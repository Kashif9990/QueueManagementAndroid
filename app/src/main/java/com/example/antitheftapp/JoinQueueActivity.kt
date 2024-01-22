package com.example.antitheftapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.antitheftapp.helper.QueueDetail
import com.example.antitheftapp.helper.QueueSubscription
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class JoinQueueActivity : AppCompatActivity() {

    private lateinit var buttonJoin: Button
    private lateinit var buttonQrCode: Button
    private lateinit var editTextQueueId: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queue_join)


        editTextQueueId = findViewById<EditText>(R.id.editTextQueueId)
        buttonJoin = findViewById<Button>(R.id.buttonJoin)
        buttonQrCode = findViewById<Button>(R.id.buttonLoadWithQR)

        buttonJoin.setOnClickListener {
            val queueId = editTextQueueId.text.toString()

            if (queueId.isNotEmpty()){
                //Go Ahead for Queue Creation
                val ref = Firebase.database.reference.child("Queues")
                val query: Query = ref.orderByChild("id").equalTo(queueId)

                query.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.value == null){
                            Utils.showToast(this@JoinQueueActivity,"No Queue Exist for Today")
                        }else{
                            for (childSnapshot in dataSnapshot.children) {
                                val queueDetail = childSnapshot.getValue(QueueDetail::class.java)

                                val userId = FirebaseAuth.getInstance().uid!!
                                val id = FirebaseDatabase.getInstance().getReference("QueueSubscription").push().key ?: userId
                                val queueSubscription = QueueSubscription(id = queueDetail?.id, userId = userId, queueName = queueDetail?.name, averageTime = queueDetail?.averageTime)

                                Firebase.database.reference.child("QueueSubscription").child(id).setValue(queueSubscription)
                                    .addOnCompleteListener {
                                        Utils.showToast(this@JoinQueueActivity, "Added to Queue Successfully")
                                        finish()

                                    }.addOnFailureListener { err ->
                                        err.printStackTrace()
                                        Utils.showToast(this@JoinQueueActivity, "Error Occurred ${err.message}")
                                    }


                            }
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Handle error
                        println("Error: ${databaseError.message}")
                        Utils.showToast(this@JoinQueueActivity,"Error Occurred")
                    }
                })

            }else{
                Utils.showToast(this,"Fields are required")
            }
        }

        buttonQrCode.setOnClickListener {
            //Todo Add Join Queue Logic here
            Utils.showToast(this,"Load QR")
            finish()
        }
    }
}
