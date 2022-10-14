package dev.vengateshm.excel_to_pdf

import android.content.ContentValues
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import java.io.File
import java.io.FileOutputStream

class FileConversionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_conversion)

        val str =
            "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"
        val byteArr = Base64.decode(str, Base64.DEFAULT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentValuesIp = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, "ap_sample")
                put(MediaStore.MediaColumns.MIME_TYPE,
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
            }
            val uriIp =
                contentResolver?.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValuesIp)
            contentResolver?.openOutputStream(uriIp!!)?.use {
                it.write(byteArr)
            }

            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, "ap_samplepppp")
                put(MediaStore.MediaColumns.MIME_TYPE,
                    "application/pdf")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
            }
            val uri =
                contentResolver?.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
            contentResolver?.openOutputStream(uri!!)?.use {
                it.write(byteArr)
            }
            if (uri != null) {
                convertToPdf(File(getRealPathFromURI(this, uriIp!!)),
                    File(getRealPathFromURI(this, uri)!!))
            }
        } else {
            val xlFile =
                File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    "ap_sample.xlsx")
            FileOutputStream(xlFile).use {
                it.write(byteArr)
            }
            val pdfFile =
                File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    "ap_sample.pdf")
            convertToPdf(xlFile, pdfFile)
        }
    }
}