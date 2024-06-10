package com.mitroshenko.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.mitroshenko.mynotes.db.MyDbManager
import com.mitroshenko.mynotes.db.MyIntentConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class EditActivity : AppCompatActivity() {
    val myDbManager = MyDbManager(this)
    var id = 0
    var isEditState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_activity)
        getMyIntents()

    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
    }

    fun onClickSave(view: View) {
        val edTitle = findViewById<EditText>(R.id.edTitle)
        val edDesc = findViewById<EditText>(R.id.edDesc)

        val myTitle = edTitle.text.toString()
        val myDesc = edDesc.text.toString()
        if (myTitle != "" && myDesc != "") {

            CoroutineScope(Dispatchers.Main).launch {
                if (isEditState) {
                    myDbManager.updateItem(myTitle, myDesc, id, getCurrentTime())
                } else {
                    myDbManager.insertToDb(myTitle, myDesc, getCurrentTime())
                }
                finish()
            }

        }

    }

    fun getMyIntents() {
        val edTitle = findViewById<EditText>(R.id.edTitle)
        val edDesc = findViewById<EditText>(R.id.edDesc)
        val i = intent
        if (i != null) {
            if (i.getStringExtra(MyIntentConstants.I_TITLE_KEY) != null) {
                isEditState = true
                edTitle.setText(i.getStringExtra(MyIntentConstants.I_TITLE_KEY))
                edDesc.setText(i.getStringExtra(MyIntentConstants.I_DESC_KEY))
                id = i.getIntExtra(MyIntentConstants.I_ID_KEY, 0)
            }
        }
    }
    private fun getCurrentTime():String{
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd.MM.yy kk:mm", Locale.getDefault())
        return formatter.format(time)
    }
}