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

//        textViewName = findViewById<TextView>(R.id.textViewName)
//        textViewNameExpectedH = findViewById<TextView>(R.id.textViewExpectedH)
//        textViewNameExpected = findViewById<TextView>(R.id.textViewExpected)
//        textViewNameCurrentTokenH = findViewById<TextView>(R.id.textViewCurrentH)
//        textViewNameCurrentToken = findViewById<TextView>(R.id.textViewCurrentToken)
//        textViewNameYourTokenH = findViewById<TextView>(R.id.textViewYourTokenH)
//        textViewNameYourToken = findViewById<TextView>(R.id.textViewYoursToken)
//
//        textViewNoQuese = findViewById<TextView>(R.id.textViewNoQuese)
//        buttonQueue = findViewById<Button>(R.id.buttonJoinCreate)
//
//        findViewById<Button>(R.id.buttonLogout).setOnClickListener {
//            FirebaseAuth.getInstance().signOut()
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
//
//        val userId = FirebaseAuth.getInstance().currentUser?.uid
//
//        val specific_user: Query = myRef.child(userId.toString())
//        specific_user.addListenerForSingleValueEvent(
//            object : ValueEventListener {
//                override fun onDataChange(dataSnapshot: DataSnapshot) {
//                    //here you will get the data
//                    val name: String = dataSnapshot.getValue(UserModel::class.java)?.name ?: ""
//                    val email: String = dataSnapshot.getValue(UserModel::class.java)?.email ?: ""
//                    Log.d("Datasnapshot", "$email $name")
//
//                    textViewName.text = "Hello $name \n $email"
//                }
//
//                override fun onCancelled(databaseError: DatabaseError) {
//
//                }
//            })
//
//        showHideViews()

    }

//    private fun showHideViews() {
//        textViewNameExpectedH.visibility = if (isAdmin) View.GONE else View.VISIBLE
//        textViewNameExpected.visibility = if (isAdmin) View.GONE else View.VISIBLE
//        textViewNameCurrentTokenH.visibility = if (isAdmin) View.GONE else View.VISIBLE
//        textViewNameCurrentToken.visibility = if (isAdmin) View.GONE else View.VISIBLE
//        textViewNameYourTokenH.visibility = if (isAdmin) View.GONE else View.VISIBLE
//        textViewNameYourToken.visibility = if (isAdmin) View.GONE else View.VISIBLE
//
//        textViewNoQuese.visibility = if (isAdmin) View.VISIBLE else View.GONE
//        buttonQueue.visibility = if (isAdmin) View.VISIBLE else View.GONE
//    }
}