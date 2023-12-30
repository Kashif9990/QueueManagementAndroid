package com.example.antitheftapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            // You can add your authentication logic here.
            // For simplicity, we'll check for a hardcoded username and password.

            if (username.isNotEmpty()  && password.isNotEmpty()) {
                // Authentication successful, open the next activity (e.g., MainActivity)
                loginUser(username,password)
            } else {
                // Authentication failed, display an error message or toast.
                // For simplicity, we'll show a toast message.
                showToast("Invalid username or password")
            }
        }
    }

    fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Login successful
                    val user = auth.currentUser
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    showToast("Sorry, Authentication Failed, please try again!")
                    // Login failed
                    Log.w("Login", "signInWithEmail:failure", task.exception)
                }
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}