package com.example.week8_r1_advancedstudyapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.week8_r1_advancedstudyapp.fragments.KotlinFragment


class MyDBHelper (context: Context): SQLiteOpenHelper(context,"detailsAK.db", null ,1) {

    var sqliteDatabase: SQLiteDatabase =writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL("create table study (Myid  integer primary key autoincrement,S text , Titel text , Descr text)")
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

//-------------------------------------------------------------------------

    fun save_date(study: String,titel: String,desc: String): Long {
        var cv= ContentValues()
        cv.put("S",study)
        cv.put("Titel",titel)
        cv.put("Descr",desc)

        var status=sqliteDatabase.insert("study",null,cv)
        return status
    }

    //-------------------------------------------------------------------------



    @SuppressLint("Range")
    fun retrive(s:String): ArrayList<Study> {
        var c: Cursor =sqliteDatabase.query("study",null,"S= ?", arrayOf(s),null,null,null)

        c.moveToFirst()

        var listS= ArrayList<Study>()

        while (!c.isAfterLast) {
            var id   =c.getInt(c.getColumnIndex("Myid"))
            var titel = c.getString(c.getColumnIndex("Titel"))
            var desc = c.getString(c.getColumnIndex("Descr"))

            var ob=Study( id,titel,desc)

            listS.add(ob)

            c.moveToNext()
        }
        return listS

    }

    //-------------------------------------------------------------------------

    fun update(idn: Int, study:String,titel:String,des:String) {
        sqliteDatabase = writableDatabase
        var cv = ContentValues()
        cv.put("S",study)
        cv.put("Titel",titel)
        cv.put("Descr",des)

        sqliteDatabase.update("study", cv, "Myid = ?", arrayOf(idn.toString()))


    }

    //-------------------------------------------------------------------------

    fun delete(idn: Int) {
        sqliteDatabase = writableDatabase
        sqliteDatabase.delete("study", "Myid = ?", arrayOf(idn.toString()))


    }


}
