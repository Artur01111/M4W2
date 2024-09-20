package com.example.m4w2.ui

import android.app.Application
import androidx.room.Room
import com.example.m4w2.ui.data.db.AppDataBase

class App : Application() {

    companion object {
        var appDatabase: AppDataBase? = null
    }

    override fun onCreate() {
        super.onCreate()
        getInstance()
    }

    fun getInstance(): AppDataBase? {
        if (appDatabase != null) {
            appDatabase = applicationContext?.let {
                Room.databaseBuilder(it, AppDataBase::class.java, "note.database")
                    .fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDatabase
    }
}