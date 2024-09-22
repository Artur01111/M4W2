package com.example.m4w2.ui.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.m4w2.ui.data.models.NoteModel

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteModel: NoteModel)

    @Query("SELECT * FROM noteModel")
    fun getAll(): LiveData<List<NoteModel >>

    @Delete
    fun deleteNote(noteModel: NoteModel)

    @Query("SELECT * FROM noteModel WHERE id = :id")
    fun getNoteById(id:Int): NoteModel?

    @Update
    fun updateNote(noteModel: NoteModel)

}