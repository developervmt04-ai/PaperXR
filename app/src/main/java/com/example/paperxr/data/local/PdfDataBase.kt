package com.example.paperxr.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.paperxr.data.model.PdfData
import com.example.paperxr.helper.Converters


@Database(entities = [PdfData::class], version = 4)
@TypeConverters(Converters::class)
abstract class PdfDataBase: RoomDatabase()  {

abstract val dao: PdfDao
}