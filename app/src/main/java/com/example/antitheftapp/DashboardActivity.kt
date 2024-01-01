package com.example.antitheftapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener


class DashboardActivity : AppCompatActivity() {

    private lateinit var textViewName: TextView
    var myRef = FirebaseDatabase.getInstance().getReference("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        textViewName = findViewById<TextView>(R.id.textViewName)

        findViewById<Button>(R.id.buttonLogout).setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val userId = FirebaseAuth.getInstance().currentUser?.uid

        val specific_user: Query = myRef.child(userId.toString())
        specific_user.addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    //here you will get the data
                    val userModel = dataSnapshot.getValue(UserModel::class.java)
                    val name: String = userModel!!.name
                    val email: String = userModel!!.email

                    Log.d("Datasnapshot", "$email $name")

                    textViewName.text = "Hello $name \n $email"
                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })



        //textViewName.text =



    }
}