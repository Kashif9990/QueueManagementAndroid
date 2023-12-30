package com.example.antitheftapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class DashboardActivity : AppCompatActivity() {

    private lateinit var textViewName: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        textViewName = findViewById<TextView>(R.id.textViewName)

        val userId = FirebaseAuth.getInstance().currentUser?.uid



        //textViewName.text =



    }
}