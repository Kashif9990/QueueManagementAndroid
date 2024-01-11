package com.example.antitheftapp

import android.annotation.SuppressLint
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener


class AdminDashboardActivity : AppCompatActivity() {

//    private lateinit var textViewName: TextView
//    private lateinit var textViewNameExpectedH: TextView
//    private lateinit var textViewNameExpected: TextView
//    private lateinit var textViewNameCurrentTokenH: TextView
//    private lateinit var textViewNameCurrentToken: TextView
//    private lateinit var textViewNameYourTokenH: TextView
//    private lateinit var textViewNameYourToken: TextView
//
//    private lateinit var textViewNoQuese: TextView
//    private lateinit var buttonQueue: Button
//
//    var database = FirebaseDatabase.getInstance()
//    var myRef = database.getReference("Users")


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_admin)

        findViewById<Button>(R.id.buttonLogout).setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}