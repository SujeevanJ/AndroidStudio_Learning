package com.example.myapplication_2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ChatActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MessageAdapter
    private val messageList = mutableListOf<Message>()

    private lateinit var input: EditText   // ✅ moved to class level

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // UI
        recyclerView = findViewById(R.id.recyclerView)
        input = findViewById(R.id.messageInput)
        val button = findViewById<Button>(R.id.sendButton)
        val micBtn = findViewById<ImageButton>(R.id.micButton)

        // Firebase
        val db = FirebaseFirestore.getInstance()
        val currentUser = FirebaseAuth.getInstance().currentUser!!.uid
        val receiverId = intent.getStringExtra("receiverId")!!

        // chatId
        val chatId = if (currentUser < receiverId)
            currentUser + receiverId
        else
            receiverId + currentUser

        // RecyclerView
        adapter = MessageAdapter(messageList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 🔄 Real-time messages
        db.collection("chats")
            .document(chatId)
            .collection("messages")
            .orderBy("timestamp")
            .addSnapshotListener { value, error ->

                if (error != null || value == null) return@addSnapshotListener

                messageList.clear()

                for (doc in value) {
                    val text = doc.getString("text") ?: ""
                    val senderId = doc.getString("senderId") ?: ""

                    val isMe = senderId == currentUser
                    messageList.add(Message(text, isMe))
                }

                adapter.notifyDataSetChanged()
                recyclerView.scrollToPosition(messageList.size - 1)
            }

        // 🎤 Voice input
        micBtn.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now...")

            startActivityForResult(intent, 100)
        }

        // ✉️ Send message
        button.setOnClickListener {
            val text = input.text.toString().trim()

            if (text.isNotEmpty()) {
                val message = hashMapOf(
                    "text" to text,
                    "senderId" to currentUser,
                    "timestamp" to System.currentTimeMillis()
                )

                db.collection("chats")
                    .document(chatId)
                    .collection("messages")
                    .add(message)

                input.text.clear()
            }
        }
    }

    // 🎤 Voice result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val spokenText = result?.get(0) ?: ""

            input.setText(spokenText) // ✅ no findViewById again
        }
    }
}