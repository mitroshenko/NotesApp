package com.mitroshenko.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mitroshenko.mynotes.db.MyDbManager

class MainActivity : AppCompatActivity() {
    //Создаем манагера
    val myDbManager = MyDbManager (this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onClickSave(view: View) {
        myDbManager.openDb()
        myDbManager.insertToDb()
    }
}