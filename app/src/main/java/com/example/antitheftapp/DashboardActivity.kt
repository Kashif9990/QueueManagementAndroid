package com.example.antitheftapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.antitheftapp.helper.QueueDetail
import com.example.antitheftapp.helper.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database


class DashboardActivity : AppCompatActivity() {

    private lateinit var textViewName: TextView
    private lateinit var textViewNameExpectedH: TextView
    private lateinit var textViewNameExpected: TextView
    private lateinit var textViewNameCurrentTokenH: TextView
    private lateinit var textViewNameCurrentToken: TextView
    private lateinit var textViewNameYourTokenH: TextView
    private lateinit var textViewNameYourToken: TextView

    private lateinit var textViewNoQuese: TextView
    private lateinit var buttonQueue: Button

    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("Users")


//    private var hasNotJoined = true

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        textViewName = findViewById<TextView>(R.id.textViewName)
        textViewNameExpectedH = findViewById<TextView>(R.id.textViewExpectedH)
        textViewNameExpected = findViewById<TextView>(R.id.textViewExpected)
        textViewNameCurrentTokenH = findViewById<TextView>(R.id.textViewCurrentH)
        textViewNameCurrentToken = findViewById<TextView>(R.id.textViewCurrentToken)
        textViewNameYourTokenH = findViewById<TextView>(R.id.textViewYourTokenH)
        textViewNameYourToken = findViewById<TextView>(R.id.textViewYoursToken)

        textViewNoQuese = findViewById<TextView>(R.id.textViewNoQuese)
        buttonQueue = findViewById<Button>(R.id.buttonJoinCreate)

        findViewById<Button>(R.id.buttonLogout).setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val userId = FirebaseAuth.getInstance().currentUser?.uid

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

        //QueueSubscription




        showHideViews()


        buttonQueue.setOnClickListener{
            val intent = Intent(this, JoinQueueActivity::class.java)
            startActivity(intent)
        }

    }

    private fun showHideViews(hasNotJoined: Boolean = true) {
        textViewNameExpectedH.visibility = if (hasNotJoined) View.GONE else View.VISIBLE
        textViewNameExpected.visibility = if (hasNotJoined) View.GONE else View.VISIBLE
        textViewNameCurrentTokenH.visibility = if (hasNotJoined) View.GONE else View.VISIBLE
        textViewNameCurrentToken.visibility = if (hasNotJoined) View.GONE else View.VISIBLE
        textViewNameYourTokenH.visibility = if (hasNotJoined) View.GONE else View.VISIBLE
        textViewNameYourToken.visibility = if (hasNotJoined) View.GONE else View.VISIBLE

        textViewNoQuese.visibility = if (hasNotJoined) View.VISIBLE else View.GONE
        buttonQueue.visibility = if (hasNotJoined) View.VISIBLE else View.GONE
    }


    private fun getActiveQueue() {
        val ref = Firebase.database.reference.child("Queues")
        val userId = FirebaseAuth.getInstance().uid!!
        val query: Query = ref.orderByChild("userId").equalTo(userId).orderByChild("dateTime")

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.value == null){
                    showHideViews(true)

                }else{
                    val list = mutableListOf<QueueDetail>()
                    for (childSnapshot in dataSnapshot.children) {
                        val queueDetail = childSnapshot.getValue(QueueDetail::class.java)
                        println("Name: ${queueDetail?.name}, AvgTime: ${queueDetail?.averageTime}")
                        list.add(queueDetail!!)
                        }
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
                println("Error: ${databaseError.message}")
                Utils.showToast(this@DashboardActivity,"Error Ocurred")
            }
        })
    }
}