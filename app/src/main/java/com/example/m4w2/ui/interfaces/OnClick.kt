package com.example.m4w2.ui.interfaces

import com.example.m4w2.data.models.NoteModel

interface OnClick {
    fun onItemClick(noteModel: NoteModel)
}