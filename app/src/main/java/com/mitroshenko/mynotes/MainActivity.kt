package com.mitroshenko.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.mitroshenko.mynotes.db.MyDbManager
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    //Создаем манагера
    val myDbManager = MyDbManager (this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickSave(view: View) {
        val twTest = findViewById<EditText>(R.id.twTest)
        twTest.text = ""
        myDbManager.openDb()
        val edTitle = findViewById<EditText>(R.id.edTitle)
        val edContent = findViewById<EditText>(R.id.edContent)
        myDbManager.insertToDb(edTitle.text.toString(),edContent.text.toString())
        val dataList = myDbManager.readDbData()
        for (item  in dataList) {
            twTest.append(item)
            twTest.append("\n")

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
}
