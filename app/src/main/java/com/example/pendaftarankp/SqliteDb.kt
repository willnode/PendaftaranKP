package com.example.pendaftarankp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Peserta {
    var id: Int = 0
    var name: String = ""
    var nim: String = ""
    var email: String = ""
    var password: String = ""
    var title: String? = null
    var dospem: String? = null
    var dospen: String? = null
    var date: String? = null
    constructor(name: String, nim: String, email: String, password: String) {
        this.name = name
        this.nim = nim
        this.email = email
        this.password = password
    }
    constructor() {
    }
}


class DBOpenHelper(context: Context,
                           factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME,
        factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE $TABLE_NAME ( " +
                "id INTEGER PRIMARY KEY, " +
                "nim TEXT NOT NULL UNIQUE, " +
                "name TEXT NOT NULL, " +
                "email TEXT NOT NULL UNIQUE, " +
                "password TEXT NOT NULL, " +
                "title TEXT, " +
                "dospem TEXT, " +
                "dospen TEXT," +
                "date TEXT" +
                " )")
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun register(peserta: Peserta) {
        val values = ContentValues()
        values.put("nim", peserta.nim)
        values.put("name", peserta.name)
        values.put("email", peserta.email)
        values.put("password", peserta.password)
        val db = this.writableDatabase
        db.insert("$TABLE_NAME", null, values)
        db.close()
    }
    private fun extract(it: Cursor) : Peserta {
        val r = Peserta()
        r.id = it.getInt(it.getColumnIndex("id"))
        r.nim = it.getString(it.getColumnIndex("nim"))
        r.name = it.getString(it.getColumnIndex("name"))
        r.email = it.getString(it.getColumnIndex("email"))
        r.password = it.getString(it.getColumnIndex("password"))
        r.title = it.getString(it.getColumnIndex("title"))
        r.dospem = it.getString(it.getColumnIndex("dospem"))
        r.dospen = it.getString(it.getColumnIndex("dospen"))
        r.date = it.getString(it.getColumnIndex("date"))
        return r
    }
    fun login(email: String, password: String): Peserta? {
        val db = this.readableDatabase
        val SELECT_QUERY = ("SELECT * FROM $TABLE_NAME WHERE email = ? AND password = ?")
        db.rawQuery(SELECT_QUERY, arrayOf(email, password)).use {
            if (it.moveToFirst()) {
               return extract(it)
            }
        }
        db.close()
        return null
    }
    fun save(peserta: Peserta) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("nim", peserta.nim)
        values.put("name", peserta.name)
        values.put("email", peserta.email)
        values.put("password", peserta.password)
        values.put("title", peserta.title)
        values.put("dospem", peserta.dospem)
        values.put("dospen", peserta.dospen)
        values.put("date", peserta.date)
        db.update(TABLE_NAME, values, "id = ?", arrayOf(peserta.id.toString()))
        db.close()
    }
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "pendaftarankp.db"
        private val TABLE_NAME = "peserta"
    }
}

class SharedData private constructor(context: Context) {
    var dbo: DBOpenHelper = DBOpenHelper(context, null)
    var user: Peserta? = null

    companion object : SingletonHolder<SharedData, Context>(::SharedData)
}

open class SingletonHolder<out T: Any, in A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator
    @Volatile private var instance: T? = null

    fun getInstance(arg: A): T {
        val checkInstance = instance
        if (checkInstance != null) {
            return checkInstance
        }

        return synchronized(this) {
            val checkInstanceAgain = instance
            if (checkInstanceAgain != null) {
                checkInstanceAgain
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}