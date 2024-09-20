package com.example.m4w2.ui.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.m4w2.ui.data.db.daos.NoteDao
import com.example.m4w2.ui.data.models.NoteModel

@Database(entities = [NoteModel::class], version = 1)

abstract class AppDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

}