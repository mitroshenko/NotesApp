package com.mitroshenko.mynotes.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

//Создание класса для записи в БД
class MyDbManager(context: Context) {
    val myDbHelper = MyDbHelper(context)
    var db: SQLiteDatabase? = null
//открыть БД
    fun openDb() {
        db = myDbHelper.writableDatabase
    }

    //Функция для записи
    fun insertToDb(title: String, content: String) {
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_TITLE, title)
            put(MyDbNameClass.COLUMN_NAME_CONTENT, content)

        }
        db?.insert(MyDbNameClass.TABLE_NAME, null, values)
    }
    fun readDbData (): ArrayList<String> {
        val dataList = ArrayList<String>()

        //Заполняем данными
        //Считываем с помощью курсора, помещаем данные в курсор
        val cursor = db?.query(MyDbNameClass.TABLE_NAME, null, null, null,null, null, null)


        while (cursor?.moveToNext()!!) {
            val dataText = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_TITLE))
            dataList.add(dataText.toString())
        }
        cursor.close()
        return dataList
    }
    fun closeDb() {
        myDbHelper.close()
    }

}