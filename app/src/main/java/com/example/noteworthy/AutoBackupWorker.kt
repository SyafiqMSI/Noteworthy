package com.example.noteworthy

import android.app.Application
import android.content.Context
import android.net.Uri
import androidx.documentfile.provider.DocumentFile
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.noteworthy.miscellaneous.Export
import com.example.noteworthy.miscellaneous.IO
import com.example.noteworthy.miscellaneous.Operations
import com.example.noteworthy.preferences.AutoBackup
import com.example.noteworthy.preferences.Preferences
import com.example.noteworthy.room.Converters
import com.example.noteworthy.room.NoteworthyDatabase
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.zip.ZipOutputStream

class AutoBackupWorker(private val context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        val app = context.applicationContext as Application
        val preferences = Preferences.getInstance(app)
        val backupPath = preferences.autoBackup.value

        if (backupPath != AutoBackup.emptyPath) {
            val uri = Uri.parse(backupPath)
            val folder = requireNotNull(DocumentFile.fromTreeUri(app, uri))

            if (folder.exists()) {
                val formatter = SimpleDateFormat("yyyyMMdd HHmmss '(Noteworthy Backup)'", Locale.ENGLISH)
                val name = formatter.format(System.currentTimeMillis())
                val file = requireNotNull(folder.createFile("application/zip", name))
                val outputStream = requireNotNull(app.contentResolver.openOutputStream(file.uri))

                val zipStream = ZipOutputStream(outputStream)

                val database = NoteworthyDatabase.getDatabase(app)
                database.checkpoint()

                Export.backupDatabase(app, zipStream)

                val mediaRoot = IO.getExternalImagesDirectory(app)
                database.getBaseNoteDao().getAllImages()
                    .asSequence()
                    .flatMap(Converters::jsonToImages)
                    .forEach { image ->
                        try {
                            Export.backupImage(zipStream, mediaRoot, image)
                        } catch (exception: Exception) {
                            Operations.log(app, exception)
                        }
                    }

                zipStream.close()
            }
        }

        return Result.success()
    }
}