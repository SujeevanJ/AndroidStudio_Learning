package com.example.myapplication_2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        val name = findViewById<EditText>(R.id.name)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val button = findViewById<Button>(R.id.signupBtn)

        button.setOnClickListener {
            val userName = name.text.toString()
            val userEmail = email.text.toString()
            val userPass = password.text.toString()

            auth.createUserWithEmailAndPassword(userEmail, userPass)
                .addOnSuccessListener {

                    val userId = auth.currentUser!!.uid

                    val user = hashMapOf(
                        "name" to userName,
                        "email" to userEmail
                    )

                    db.collection("users").document(userId).set(user)

                    startActivity(Intent(this, ChatListActivity::class.java))
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
        }
    }
}

