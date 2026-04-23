package com.example.calculatorapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.TextView
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val num1 = findViewById<EditText>(R.id.num1)
        val num2 = findViewById<EditText>(R.id.num2)
        val resultText = findViewById<TextView>(R.id.resultText)
        val addBtn = findViewById<Button>(R.id.addbtn)
        val subBtn = findViewById<Button>(R.id.subbtn)
        val multiplyBtn = findViewById<Button>(R.id.MultiplyBtn)
        val divideBtn = findViewById<Button>(R.id.DivideBtn)


        addBtn.setOnClickListener {
            val n1 = num1.text.toString().toDoubleOrNull() ?: 0.0
            val n2 = num1.text.toString().toDoubleOrNull() ?: 0.0

            val result = n1 + n2
            resultText.text = "Result: $result"


        }
        subBtn.setOnClickListener {
            val n1 = num1.text.toString().toDoubleOrNull() ?: 0.0
            val n2 = num1.text.toString().toDoubleOrNull() ?: 0.0

            val result = n1 - n2
            resultText.text = "Result: $result"


        }
        multiplyBtn.setOnClickListener {
            val n1 = num1.text.toString().toDoubleOrNull() ?: 0.0
            val n2 = num1.text.toString().toDoubleOrNull() ?: 0.0

            val result = n1 * n2
            resultText.text = "Result: $result"


        }
        divideBtn.setOnClickListener {
            val n1 = num1.text.toString().toDoubleOrNull() ?: 0.0
            val n2 = num1.text.toString().toDoubleOrNull() ?: 0.0

            val result = n1 / n2
            resultText.text = "Result: $result"


        }



    }
}