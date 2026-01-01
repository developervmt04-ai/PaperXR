package com.example.paperxr.ui.pdflist

import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.example.paperxr.data.model.PdfData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PdfRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    suspend fun getAllPdfs(): List<PdfData> = withContext(Dispatchers.IO) {
        val pdfs = mutableListOf<PdfData>()

        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.SIZE,
            MediaStore.Files.FileColumns.DATE_ADDED
        )

        val selection = "${MediaStore.Files.FileColumns.MIME_TYPE}=?"
        val selectionArgs = arrayOf("application/pdf")
        val sortOrder = "${MediaStore.Files.FileColumns.DATE_ADDED} DESC"

        val queryUri = MediaStore.Files.getContentUri("external")

        context.contentResolver.query(queryUri, projection, selection, selectionArgs, sortOrder)?.use { cursor ->
            val idCol = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
            val nameCol = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)
            val sizeCol = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)
            val dateCol = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_ADDED)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idCol)
                val name = cursor.getString(nameCol)
                val size = cursor.getLong(sizeCol)
                val date = cursor.getLong(dateCol)
                val uri: Uri = Uri.withAppendedPath(queryUri, id.toString())
                pdfs.add(PdfData(id, name, uri, size, dateAdded = date))
            }
        }

        pdfs
    }
}
