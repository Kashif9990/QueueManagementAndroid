package com.example.antitheftapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.antitheftapp.helper.QueueDetail
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import java.util.Calendar

class AddQueueActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var averageTimeEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queue_add)

        nameEditText = findViewById(R.id.editTextName)
        averageTimeEditText = findViewById(R.id.editTextQueueId)

        findViewById<Button>(R.id.buttonCreate).setOnClickListener {
            if (nameEditText.text.toString().isNotEmpty() && averageTimeEditText.text.toString().isNotEmpty()){
                //Go Ahead for Queue Creation
                val userId = FirebaseAuth.getInstance().uid!!
                val id = FirebaseDatabase.getInstance().getReference("Queues").push().key ?: userId
                val name = nameEditText.text.toString()
                val avgTime = averageTimeEditText.text.toString().toInt()
                val queueDetail = QueueDetail(id = getUID(10),userId = userId, name = name, averageTime = avgTime)

                Firebase.database.reference.child("Queues").child(id).setValue(queueDetail)
                    .addOnCompleteListener {
                        Toast.makeText(this, "Queue Created Successfully", Toast.LENGTH_LONG).show()
                        finish()

                    }.addOnFailureListener { err ->
                        err.printStackTrace()
                        Toast.makeText(this, "Error Occurred ${err.message}", Toast.LENGTH_LONG).show()
                    }
            }else{
                Utils.showToast(this,"Fields are required")
            }

        }
    }

    /**
     * @param digit is to define how many Unique digit you wnat
     * Such as 8, 10, 12 etc
     * But digit must be Min 8 Max 12
     */
    fun getUID(digit:Int): String {
        var currentMilliSeconds:String = ""+ Calendar.getInstance().timeInMillis
        var genDigit:Int = digit
        if(genDigit<8)
            genDigit = 8

        if(genDigit>12)
            genDigit = 12

        var cut = currentMilliSeconds.length - genDigit
        currentMilliSeconds = currentMilliSeconds.substring(cut);
        return currentMilliSeconds.toLong().toString()
    }
}
