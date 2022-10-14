package dev.vengateshm.excel_to_pdf

import android.content.Context
import android.os.Environment
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun Context.getFile(fileName: String, fileSuffix: String): File? {
    val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
    val fileNamePrefix = fileName.plus(dateFormat.format(Date()))
    val storageDir: File? = this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
    return File.createTempFile(fileNamePrefix, fileSuffix, storageDir)
}