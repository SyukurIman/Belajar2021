package com.example.belajar2021.bg

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.belajar2021.bg.DataBaseContract.NoteColumns.Companion.TABLE_NAME
import com.example.belajar2021.bg.DataBaseContract.NoteColumns.Companion._ID
import java.sql.SQLException
import kotlin.jvm.Throws

class NoteHelper (context: Context) {
    private var dataBaseHelper: DataBaseHelper = DataBaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object {
        private const val DATABASE_TABLE = TABLE_NAME

        private var INSTANCE: NoteHelper? = null
        fun getInstance(context: Context): NoteHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: NoteHelper(context)
            }
    }

    @Throws(SQLException::class)
    fun open(){
        database = dataBaseHelper.writableDatabase
    }

    fun close(){
        dataBaseHelper.close()

        if (database.isOpen)
            database.close()
    }

    fun queryALL(): Cursor{
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC"
        )
    }

    fun queryById(id: String): Cursor{
        return database.query(
            DATABASE_TABLE,
            null,
            "$_ID = ?",
            null,
            null,
            null,
            null,
        )
    }

    fun insert(value: ContentValues?): Long{
        return database.insert(DATABASE_TABLE, null, value)
    }

    fun update(id: String, value: ContentValues?): Int{
        return database.update(DATABASE_TABLE, value, "$_ID = ?", arrayOf(id))
    }

    fun deleteById(id: String): Int{
        return database.delete(DATABASE_TABLE, "$_ID = '$id'",null)
    }
}