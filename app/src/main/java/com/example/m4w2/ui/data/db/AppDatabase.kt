package com.example.m4w2.ui.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.m4w2.ui.data.models.NoteModel

@Database(entities = [NoteModel:: class], version = 3)
abstract class AppDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

}