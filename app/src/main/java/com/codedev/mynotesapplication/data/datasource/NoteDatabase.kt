package com.codedev.mynotesapplication.data.datasource

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codedev.mynotesapplication.domain.models.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        private var instance: NoteDatabase? = null

        operator fun invoke(application: Application) = instance?: synchronized(this) {
            instance?:createDatabase(application).also {
                instance = it
            }
        }

        private fun createDatabase(application: Application) : NoteDatabase {
            return Room.databaseBuilder(
                application,
                NoteDatabase::class.java,
                "note_db"
            ).build()
        }
    }
}