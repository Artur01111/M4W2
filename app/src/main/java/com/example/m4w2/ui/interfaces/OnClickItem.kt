package com.example.m4w2.ui.interfaces

import com.example.m4w2.ui.data.models.NoteModel

interface OnClickItem {

    fun onLongClick(noteModel: NoteModel)

    fun onClick(noteModel: NoteModel)
}