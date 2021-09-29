package com.codedev.mynotesapplication.di

import android.app.Application
import androidx.room.Room
import com.codedev.mynotesapplication.data.datasource.NoteDao
import com.codedev.mynotesapplication.data.datasource.NoteDatabase
import com.codedev.mynotesapplication.data.repository.NoteRepository
import com.codedev.mynotesapplication.domain.repository.NoteRepositoryImpl
import com.codedev.mynotesapplication.domain.usecases.*
import com.codedev.mynotesapplication.domain.util.NoteUseCases
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
    fun provideNoteDatabase(application: Application): NoteDatabase {
        return Room.databaseBuilder(
            application,
            NoteDatabase::class.java,
            "note_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNote = GetNote(repository),
            getAllNotes = GetAllNotes(repository),
            deleteNote = DeleteNote(repository),
            insertNote = InsertNote(repository),
            updateNote = UpdateNote(repository),
            searchNote = SearchNote(repository)
        )
    }

}