package com.example.paperxr.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paperxr.data.model.PdfData
import kotlinx.coroutines.flow.Flow

@Dao
interface PdfDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)

    suspend fun addPdf(pdf: PdfData)


    @Delete
    suspend fun deletePdf(pdfData: PdfData)


    @Query("SELECT * FROM PDF_TABLE")
   fun getAllPdfs(): Flow<List<PdfData>>


}