package com.mitroshenko.mynotes.db

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mitroshenko.mynotes.MainActivity
import com.mitroshenko.mynotes.R
import java.util.Timer
import java.util.logging.Handler
import kotlin.concurrent.timerTask


class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }
    val handler = Handler()

    val runnable = Runnable {
        val i = Intent(this@StartActivity, MainActivity::class.java)
        startActivity(i)
    }
    handler.postDelayed(Runnable, 4000)
}