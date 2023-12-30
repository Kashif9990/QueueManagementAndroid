package com.example.antitheftapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dbRef = FirebaseDatabase.getInstance().getReference("Users")
            val btnRegister: Button = findViewById(R.id.btnRegister)
            // Retrieve data from EditText fields
            findViewById<EditText>(R.id.editTextName).text.toString()
            findViewById<EditText>(R.id.editTextEmail).text.toString()
            findViewById<EditText>(R.id.editTextPassword).text.toString()
            findViewById<EditText>(R.id.editTextVerifyPassword).text.toString()
            findViewById<EditText>(R.id.editTextAddress).text.toString()
            findViewById<EditText>(R.id.editTextContact).text.toString()
            findViewById<EditText>(R.id.editTextAge).text.toString()


            btnRegister.setOnClickListener {
                registerUser("sr.naveed99@gmail.com","123456","Naveed","+923459647789",33,true)
            }

            // I WILL Perform registration logic here BY NAVEED

    }


    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun registerUser(email: String, password: String, name: String, mobile:String, age:Int,isAdmin:Boolean) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Registration successful
                    val user = auth.currentUser!!

                    val userModel = UserModel(user.uid,email,name,mobile,age,isAdmin)

                    dbRef.child(user.uid).setValue(userModel)
                        .addOnCompleteListener {
                            Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                        }.addOnFailureListener { err ->
                            err.printStackTrace()
                            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                        }

                    showToast("Success")
                } else {
//                    val user = auth.currentUser!!
//
//                    val userModel = UserModel(user.uid,email,name,mobile,age,isAdmin)
//
//                    dbRef.child(user.uid).setValue(userModel)
//                        .addOnCompleteListener {
//                            Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
//
//                        }.addOnFailureListener { err ->
//                            err.printStackTrace()
//                            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
//                        }




                    showToast("Error")
                    // Registration failed
                    Log.w("Registration", "createUserWithEmail:failure", task.exception)
                }
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show()
    }
}

