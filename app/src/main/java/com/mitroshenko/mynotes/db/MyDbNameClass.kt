package com.mitroshenko.mynotes.db

import android.provider.BaseColumns

object MyDbNameClass: BaseColumns {

    //Содержание таблицы1
    const val TABLE_NAME = "my_table"
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_CONTENT = "content"

    //Версия и название таблицы2
    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "MyFirstDb.db"

    //Создание таблицы3
    const val CREAT_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +

            //То что таблица содержит4
    "${BaseColumns._ID} INTEGER PRIMARY KEY,$COLUMN_NAME_TITLE TEXT,$COLUMN_NAME_CONTENT TEXT)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}