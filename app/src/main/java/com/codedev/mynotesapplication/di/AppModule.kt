package com.codedev.mynotesapplication.di

import android.app.Application
import com.codedev.mynotesapplication.data.datasource.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDao(application: Application) =
        NoteDatabase(application).noteDao

}