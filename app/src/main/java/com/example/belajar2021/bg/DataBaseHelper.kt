package com.example.belajar2021.bg

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.belajar2021.bg.DataBaseContract.NoteColumns.Companion.TABLE_NAME

internal class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "dbnoteapp"
        private const val DATABASE_VERSION = 1

        private const val SQL_CREATE_TABLE_NOTE = "CREATE TABLE $TABLE_NAME" +
                "(${DataBaseContract.NoteColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${DataBaseContract.NoteColumns.TITLE} TEXT NOT NULL," +
                "${DataBaseContract.NoteColumns.DESCRIPTION} TEXT NOT NULL," +
                "${DataBaseContract.NoteColumns.DATE} TEXT NOT NULL"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_NOTE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }


}