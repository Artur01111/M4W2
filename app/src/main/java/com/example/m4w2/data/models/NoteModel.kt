package com.example.m4w2.data.models

import android.icu.text.CaseMap.Title
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteModel")
data class  NoteModel(
    val date: String,
    val time: String,
    val title: String,
    val description: String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0

}