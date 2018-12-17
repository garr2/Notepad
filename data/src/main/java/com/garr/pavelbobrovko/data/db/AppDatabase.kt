package com.garr.pavelbobrovko.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.garr.pavelbobrovko.data.db.dao.NoteDao
import com.garr.pavelbobrovko.data.db.entity.NoteDb

@Database(entities = [NoteDb::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    companion object {
        const val DATABASE_NAME = "NotesDB"

        fun getInstance(context: Context): AppDatabase{
            return Room.databaseBuilder(context, AppDatabase::class.java
                    , DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }

    abstract fun getNoteDao(): NoteDao
}