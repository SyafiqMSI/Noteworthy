package com.example.noteworthy.miscellaneous

import android.app.Application
import com.example.noteworthy.room.Image
import com.example.noteworthy.room.NoteworthyDatabase
import java.io.File
import java.io.FileInputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

object Export {

    fun backupDatabase(app: Application, zipStream: ZipOutputStream) {
        val entry = ZipEntry(NoteworthyDatabase.DatabaseName)
        zipStream.putNextEntry(entry)

        val file = app.getDatabasePath(NoteworthyDatabase.DatabaseName)
        val inputStream = FileInputStream(file)
        inputStream.copyTo(zipStream)
        inputStream.close()

        zipStream.closeEntry()
    }

    fun backupImage(zipStream: ZipOutputStream, mediaRoot: File?, image: Image) {
        val file = if (mediaRoot != null) File(mediaRoot, image.name) else null
        if (file != null && file.exists()) {
            val entry = ZipEntry("Images/${image.name}")
            zipStream.putNextEntry(entry)

            val inputStream = FileInputStream(file)
            inputStream.copyTo(zipStream)
            inputStream.close()

            zipStream.closeEntry()
        }
    }
}