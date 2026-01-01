package com.example.paperxr.di

import android.content.Context
import com.example.paperxr.data.local.PdfDao
import com.example.paperxr.data.local.PdfDataBase
import com.example.paperxr.ui.pdflist.PdfRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApModule {


    @Provides
    @Singleton

    fun provideDataBase(@ApplicationContext app: Context):
            PdfDataBase = androidx.room.Room.databaseBuilder(app,
                PdfDataBase::class.java,"PdfDb"
            ).fallbackToDestructiveMigration().build()


@Provides
fun provideDao(pdfDataBase: PdfDataBase): PdfDao=pdfDataBase.dao

    @Provides
    @Singleton
    fun providePdfRepository(
        @ApplicationContext context: Context
    ): PdfRepository {
        return PdfRepository(context)
    }

}
