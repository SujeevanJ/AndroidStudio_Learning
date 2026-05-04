package com.example.myapplication_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ChatListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter
    private val userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)

        recyclerView = findViewById(R.id.recyclerViewUsers)

        adapter = UserAdapter(userList) { user ->
            val intent = Intent(this, ChatActivity::class.java)
            intent.putExtra("receiverId", user.id)
            intent.putExtra("receiverName", user.name)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val db = FirebaseFirestore.getInstance()
        val currentUser = FirebaseAuth.getInstance().currentUser!!.uid

        // 🚪 Logout
        findViewById<Button>(R.id.logoutBtn).setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        // 👥 Load users
        db.collection("users").get()
            .addOnSuccessListener { result ->
                userList.clear()

                for (doc in result) {
                    if (doc.id != currentUser) {
                        val user = User(
                            doc.id,
                            doc.getString("name") ?: "",
                            doc.getString("email") ?: ""
                        )
                        userList.add(user)
                    }
                }

                adapter.notifyDataSetChanged()
            }
    }
}