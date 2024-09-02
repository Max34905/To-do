package com.example.sqlitetutorial

import MyDB.DbManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var dbManager: DbManager
    lateinit var todoList: TextView
    lateinit var titleTextForm: EditText
    lateinit var todoTextForm: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        dbManager = DbManager(this)
        dbManager.openDb()
        todoList = findViewById<TextView>(R.id.todoList)
        titleTextForm = findViewById<EditText>(R.id.titleTextForm)
        todoTextForm = findViewById<EditText>(R.id.todoTextForm)
        dbManager.showInfo(todoList)
    }

    fun saveData(view: View) {
        try {
                dbManager.writeToTable(
                titleTextForm.text.toString(),
                todoTextForm.text.toString())
        }catch (e: Exception){
            Log.e("DbManager", "Error opening database", e)
        }
        dbManager.showInfo(todoList)

    }

    fun clearData(view: View) {
        dbManager.clearData()
        dbManager.showInfo(todoList)
        todoList.text = ""
    }

    override fun onDestroy() {
        super.onDestroy()
        dbManager.closeDb()
    }
}