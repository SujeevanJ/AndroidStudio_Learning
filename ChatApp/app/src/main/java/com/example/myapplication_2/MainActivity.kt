package com.example.myapplication_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val input = findViewById<EditText>(R.id.message_input)
        val button = findViewById<Button>(R.id.send_button)
        val chatter = findViewById<Button>(R.id.chat_button)

        button.setOnClickListener {
            val msg = input.text.toString()
            Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
        }
        chatter.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }
    }
}

