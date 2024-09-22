package com.example.m4w2.data.models

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteModel")
data class NoteModel(
    val title: String,
    val content: String,
    val color: Int = Color.BLACK,
    val time: String,
    val date: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}