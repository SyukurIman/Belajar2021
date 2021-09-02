package com.example.belajar2021.bg

import android.provider.BaseColumns

internal class DataBaseContract {

    internal class  NoteColumns: BaseColumns{
        companion object{
            const val TABLE_NAME = "note"
            const val _ID = "_id"
            const val TITLE = "title"
            const val DESCRIPTION = "description"
            const val DATE = "date"
        }
    }
}