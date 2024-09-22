package com.example.m4w2

import android.app.Application
import androidx.room.Room
import com.example.m4w2.data.db.AppDataBase
import com.example.m4w2.utils.PreferenceHelper

class App: Application() {

    companion object{
        var appDataBase: AppDataBase? = null
    }
    override fun onCreate(){
        super.onCreate()
        val sharedPreferences = PreferenceHelper
        sharedPreferences.unit(this)
        getInstance()

    }

    fun getInstance(): AppDataBase? {
        if (appDataBase == null) {
            appDataBase = applicationContext?.let {
                Room.databaseBuilder(
                    it,
                    AppDataBase::class.java,
                    "note.db"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDataBase

    }
}