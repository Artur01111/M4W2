package com.example.m4w2.interfaces

import com.example.m4w2.data.models.NoteModel

interface OnClickItem {
    fun onLongClick(noteModel: NoteModel)
    fun onClick(noteModel: NoteModel)
}