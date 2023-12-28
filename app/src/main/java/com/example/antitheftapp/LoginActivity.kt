package com.example.antitheftapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnRegister: Button = findViewById(R.id.btnRegister)
        btnRegister.setOnClickListener {
            // Retrieve data from EditText fields
            findViewById<EditText>(R.id.editTextName).text.toString()
            findViewById<EditText>(R.id.editTextEmail).text.toString()
            findViewById<EditText>(R.id.editTextPassword).text.toString()
            findViewById<EditText>(R.id.editTextVerifyPassword).text.toString()
            findViewById<EditText>(R.id.editTextAddress).text.toString()
            findViewById<EditText>(R.id.editTextContact).text.toString()
            findViewById<EditText>(R.id.editTextAge).text.toString()
//            if (register)

            // I WILL Perform registration logic here BY NAVEED

        }
    }
}

