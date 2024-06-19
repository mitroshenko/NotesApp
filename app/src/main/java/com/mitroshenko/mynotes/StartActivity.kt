package com.mitroshenko.mynotes

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class StartActivity : AppCompatActivity() {

    private val handler = Handler()
    private val runnable = Runnable {
        val i = Intent(this@StartActivity, MainActivity::class.java)
        startActivity(i)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        handler.postDelayed(runnable, 2000)
    }
}