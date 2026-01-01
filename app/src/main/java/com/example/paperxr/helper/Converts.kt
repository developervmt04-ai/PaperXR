package com.example.paperxr.helper

import android.net.Uri
import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromUri(uri: Uri):String?{
        return uri.toString()
    }

    @TypeConverter
    fun toUri(uriToString:String): Uri?{
        return uriToString?.let { Uri.parse(it)}

    }
}