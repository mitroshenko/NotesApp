package com.mitroshenko.mynotes.db

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.mitroshenko.mynotes.MainActivity
import com.mitroshenko.mynotes.R


class StartActivity : AppCompatActivity() {

    private val handler = Handler()
    private val runnable = Runnable {
        val i = Intent(this@StartActivity, MainActivity::class.java)
        startActivity(i)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        handler.postDelayed(runnable, 4000)
    }
}