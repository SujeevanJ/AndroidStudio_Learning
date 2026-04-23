package com.example.myapp1_loginapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailInput = findViewById<EditText>(R.id.emailInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val loginBtn = findViewById<Button>(R.id.loginBtn)

        loginBtn.setOnClickListener {

            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email == "sample@sample.com" && password == "sample") {
                Toast.makeText(this, "Login Successful ✅", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Login Failed ❌", Toast.LENGTH_SHORT).show()
            }
        }
    }
}