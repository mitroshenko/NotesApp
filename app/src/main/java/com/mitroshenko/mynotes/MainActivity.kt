package com.mitroshenko.mynotes

import android.content.Intent
import android.icu.lang.UCharacter.VerticalOrientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mitroshenko.mynotes.db.MyAdapter
import com.mitroshenko.mynotes.db.MyDbManager
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    //Создаем манагера
    val myDbManager = MyDbManager(this)
    val myAdapter = MyAdapter(ArrayList())
    val rcView = findViewById<RecyclerView>(R.id.rcView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
        fillAdapter()
    }

    fun onClickNew(view: View) {
        val i = Intent(this,EditActivity::class.java)
        startActivity(i)
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
    fun init(){
        rcView.layoutManager = LinearLayoutManager(this)
        rcView.adapter = myAdapter
    }
    fun fillAdapter(){
        myAdapter.updateAdapter(myDbManager.readDbData())
    }
}
