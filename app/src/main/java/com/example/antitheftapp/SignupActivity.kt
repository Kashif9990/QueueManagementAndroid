package com.example.antitheftapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import com.example.antitheftapp.helper.UserModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {

    private lateinit var dbRef: DatabaseReference
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        dbRef = FirebaseDatabase.getInstance().getReference("Users")
        val btnRegister: Button = findViewById(R.id.btnRegister)
        // Retrieve data from EditText fields


        btnRegister.setOnClickListener {
            val isAdmin = findViewById<Switch>(R.id.switchAdmin).isChecked
            val name = findViewById<EditText>(R.id.editTextName).text.toString()
            val email = findViewById<EditText>(R.id.editTextEmail).text.toString()
            val password = findViewById<EditText>(R.id.editTextPassword).text.toString()
            //val email = findViewById<EditText>(R.id.editTextVerifyPassword).text.toString()
            val address = findViewById<EditText>(R.id.editTextAddress).text.toString()
            val contact = findViewById<EditText>(R.id.editTextContact).text.toString()
            val age = findViewById<EditText>(R.id.editTextAge).text.toString()

            val ageValue = (if(age.isEmpty()){ "0" }else {age}).toInt()
            registerUser(email,password,name,contact,ageValue,address, isAdmin)
        }

        // I WILL Perform registration logic here BY NAVEED

    }

    private fun registerUser(email: String, password: String, name: String, mobile:String, age:Int, address:String, isAdmin:Boolean) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Registration successful
                    val user = auth.currentUser!!
                    //Add data to realtime database
                    createUserData(user.uid, email, name, mobile, age, address, isAdmin)
                } else {
                    // Registration failed
                    showToast("Error Occurred, please try again later")
                    Log.w("Registration", "createUserWithEmail:failure", task.exception)
                }
            }
    }

    private fun createUserData(
        userId: String,
        email: String,
        name: String,
        mobile: String,
        age: Int,
        address: String,
        admin: Boolean
    ) {
        val userModel = UserModel(userId, email, name, mobile, age, address, admin)

        dbRef.child(userId).setValue(userModel)
            .addOnCompleteListener {
                Toast.makeText(this, "Registered Successfully, please login now", Toast.LENGTH_LONG).show()
                finish()

            }.addOnFailureListener { err ->
                err.printStackTrace()
                Toast.makeText(this, "Error Occurred ${err.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show()
    }
}

