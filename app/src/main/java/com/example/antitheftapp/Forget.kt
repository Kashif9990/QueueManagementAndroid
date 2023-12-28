package com.example.antitheftapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Forget : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget)
        val btnSendEmail: Button = findViewById(R.id.btnSendEmail)
        val btnGoBack: Button = findViewById(R.id.btnGoBack)
        val editTextEmail: EditText = findViewById(R.id.editTextEmail)

        btnSendEmail.setOnClickListener {
            // Retrieve the email entered by the user
            val email = editTextEmail.text.toString()

            // Perform the logic to send a recovery link to the email
            // You can implement your own logic or connect to a server for this functionality
            // For demonstration purposes, we'll just show a Toast message
            showToast("Recovery link sent to $email")
        }

        btnGoBack.setOnClickListener {
            // Navigate back to the previous activity or close the current one
            finish()
        }
    }

    private fun showToast(message: String) {
        // Display a Toast message
        Toast.makeText()
    }
}
