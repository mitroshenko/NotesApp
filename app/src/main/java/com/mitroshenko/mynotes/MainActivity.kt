package com.mitroshenko.mynotes
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mitroshenko.mynotes.db.MyAdapter
import com.mitroshenko.mynotes.db.MyDbManager
import com.mitroshenko.mynotes.db.StartActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    //Создаем манагера

    val myDbManager = MyDbManager(this)
    val myAdapter = MyAdapter(ArrayList(), this)
    lateinit var rcView: RecyclerView
    private var job: Job? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rcView = findViewById<RecyclerView>(R.id.rcView)
        init()
        initSearchView()
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
        fillAdapter("")
    }

    fun onClickNew(view: View) {
        val i = Intent(this, EditActivity::class.java)
        startActivity(i)
    }


    fun init() {
        rcView.layoutManager = LinearLayoutManager(this)
        val swapHelper = getSwapMg()
        swapHelper.attachToRecyclerView(rcView)
        rcView.adapter = myAdapter
    }
    private fun initSearchView () {
        val sView = findViewById<SearchView>(R.id.sView)
        sView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                fillAdapter(text!!)
                return true
            }
        })
    }

    private fun fillAdapter(text: String) {
        job?.cancel()
        job = CoroutineScope (Dispatchers.Main).launch {
            val tvNoElements = findViewById<TextView>(R.id.tvNoElements)
            val list = myDbManager.readDbData(text)
            myAdapter.updateAdapter(list)
            if (list.size > 0) {
                tvNoElements.visibility = View.GONE
            }    else {
                tvNoElements.visibility = View.VISIBLE
            }
        }

    }
    private fun getSwapMg(): ItemTouchHelper {
        return ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                myAdapter.removeItem(viewHolder.adapterPosition, myDbManager)

            }
        })
    }
}

