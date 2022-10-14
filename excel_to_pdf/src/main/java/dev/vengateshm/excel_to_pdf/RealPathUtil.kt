package dev.vengateshm.excel_to_pdf

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.DocumentsContract
import android.provider.OpenableColumns
import android.util.Log
import org.apache.poi.util.IOUtils
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.net.URLEncoder

@SuppressLint("NewApi")
fun getRealPathFromURI(context: Context, uri: Uri): String? {
    val isKitKat: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

    if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
        // DocumentProvider
        when {
            isGoogleDriveUri(uri) -> {
                return getDriveFilePath(context, uri)
            }
            isExternalStorageDocument(uri) -> { // ExternalStorageProvider
                val docId: String = DocumentsContract.getDocumentId(uri)
                val split: List<String> = docId.split(":")
                val type: String = split[0]
                // This is for checking Main Memory
                return if ("primary".equals(type, ignoreCase = true)) {
                    if (split.size > 1) {
                        context.getExternalFilesDir(null).toString() + "/" + split[1]
                    } else {
                        context.getExternalFilesDir(null).toString() + "/"
                    }
                    // This is for checking SD Card
                } else {
                    "storage" + "/" + docId.replace(":", "/")
                }
            }
            isDownloadsDocument(uri) -> {
                // DownloadsProvider
                val parcelFileDescriptor =
                    context.contentResolver.openFileDescriptor(uri, "r", null)
                parcelFileDescriptor?.let {
                    val inputStream = FileInputStream(
                        parcelFileDescriptor.fileDescriptor)
                    val file = File(
                        context.cacheDir,
                        context.contentResolver.getFileName(uri))
                    val outputStream = FileOutputStream(file)
                    IOUtils.copy(inputStream, outputStream)
                    return file.path
                }
            }
            isMediaDocument(uri) -> {
                return copyFileToInternalStorage(context, uri, context.getString(R.string.app_name))
            }
        }
    } else if ("content".equals(uri.scheme, ignoreCase = true)) {
        // MediaStore (and general)
        return if (isGooglePhotosUri(uri))
            uri.lastPathSegment
        else copyFileToInternalStorage(
            context,
            uri,
            context.getString(R.string.app_name))
    } else if ("file".equals(uri.scheme, ignoreCase = true)) {
        // File
        return uri.path
    }
    return null
}

fun ContentResolver.getFileName(uri: Uri): String {
    var name = ""
    val returnCursor = this.query(uri, null, null, null, null)
    if (returnCursor != null) {
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        name = returnCursor.getString(nameIndex)
        returnCursor.close()
    }
    return URLEncoder.encode(name, "utf-8")
}

/**
 * @param uri The Uri to check.
 * @return Whether the Uri authority is ExternalStorageProvider.
 */
fun isExternalStorageDocument(uri: Uri): Boolean {
    return "com.android.externalstorage.documents" == uri.authority
}

fun isGoogleDriveUri(uri: Uri): Boolean {
    return "com.google.android.apps.docs.storage" == uri.authority ||
            "com.google.android.apps.docs.storage.legacy" == uri.authority
}

/**
 * @param uri The Uri to check.
 * @return Whether the Uri authority is DownloadsProvider.
 */
fun isDownloadsDocument(uri: Uri): Boolean {
    return "com.android.providers.downloads.documents" == uri.authority
}

/**
 * @param uri The Uri to check.
 * @return Whether the Uri authority is MediaProvider.
 */
fun isMediaDocument(uri: Uri): Boolean {
    return "com.android.providers.media.documents" == uri.authority
}

/**
 * @param uri The Uri to check.
 * @return Whether the Uri authority is Google Photos.
 */
fun isGooglePhotosUri(uri: Uri): Boolean {
    return "com.google.android.apps.photos.content" == uri.authority
}

fun getDriveFilePath(context: Context, uri: Uri): String {
    val cursor: Cursor? = context.contentResolver.query(uri, null, null, null, null)
    if (cursor != null) {
        val nameIndex: Int = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        cursor.moveToFirst()
        val name: String = (cursor.getString(nameIndex))
        val file = File(context.cacheDir, URLEncoder.encode(name, "utf-8"))
        try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(file)
            val maxBufferSize: Int = 1 * 1024 * 1024
            val bytesAvailable: Int = inputStream!!.available()
            val bufferSize: Int = bytesAvailable.coerceAtMost(maxBufferSize)
            val buffers = ByteArray(bufferSize)
            inputStream.use { ios: InputStream ->
                outputStream.use { fileOut ->
                    while (true) {
                        val length = ios.read(buffers)
                        if (length <= 0)
                            break
                        fileOut.write(buffers, 0, length)
                    }
                    fileOut.flush()
                    fileOut.close()
                }
            }
            Log.e("File Size", "Size " + file.length())
            inputStream.close()
            Log.e("File Path", "Path " + file.path)
            Log.e("File Size", "Size " + file.length())
        } catch (e: Exception) {
            Log.e("Exception", e.message.toString())
        }
        cursor.close()
        return file.path
    }
    return ""
}

private fun copyFileToInternalStorage(context: Context, uri: Uri, newDirName: String): String {
    val cursor: Cursor? = context.contentResolver.query(
        uri,
        arrayOf(OpenableColumns.DISPLAY_NAME, OpenableColumns.SIZE),
        null,
        null,
        null)

    if (cursor != null) {
        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        cursor.moveToFirst()
        val name = cursor.getString(nameIndex)
        cursor.close()

        val output: File = if (newDirName != "") {
            val dir = File(context.filesDir.toString() + "/" + newDirName)
            if (!dir.exists()) {
                dir.mkdir()
            }
            File(
                context.filesDir.toString()
                        + "/" + newDirName + "/"
                        + URLEncoder.encode(name, "utf-8"))
        } else {
            File(context.filesDir.toString()
                    + "/" + URLEncoder.encode(name, "utf-8"))
        }
        try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(output)
            var read: Int
            val bufferSize = 1024
            val buffers = ByteArray(bufferSize)
            while (inputStream!!.read(buffers).also { read = it } != -1) {
                outputStream.write(buffers, 0, read)
            }
            inputStream.close()
            outputStream.close()
        } catch (e: java.lang.Exception) {
            Log.e("Exception", e.message!!)
        }
        return output.path
    }
    return ""
}