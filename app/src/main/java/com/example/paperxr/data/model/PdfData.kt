package com.example.paperxr.data.model

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("PDF_TABLE")
data class PdfData(
    @PrimaryKey val id: Long =0L,
    val name: String,
    val uri: Uri? =null,
    val size: Long,
    val pageCount: Long? =null,
    val isFavorites: Boolean =false,
    val dateAdded: Long

)
