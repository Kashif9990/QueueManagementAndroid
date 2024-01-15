package com.example.antitheftapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.antitheftapp.helper.PreferenceHelper
import com.example.antitheftapp.helper.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database

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

        PreferenceHelper.init(this)
        val isAdmin : Boolean = PreferenceHelper.get("isAdmin",false)

        if(isAdmin){
            proceedToNextScreen(true)
            return
        }

        if (FirebaseAuth.getInstance().currentUser != null) {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
            return
        }


        loginButton.setOnClickListener {
//            if(Utils.IS_DEMO){
//                //Only for DEMO
//                proceedToNextScreen(true)
//                return@setOnClickListener
//            }
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

        findViewById<Button>(R.id.signupButton).setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Login successful
                    val user = auth.currentUser!!
                    //Todo isAdmin Logic here
                    getUserDate(user.uid)
                //proceedToNextScreen(true)

                } else {
                    showToast("Sorry, Authentication Failed, please try again!")
                    // Login failed
                    Log.w("Login", "signInWithEmail:failure", task.exception)
                }
            }
    }

    private fun getUserDate(userId: String){
        val uidRef = Firebase.database.reference.child("Users").child(userId)
        uidRef.get().addOnCompleteListener {
            if (it.isSuccessful) {
                val user = it.result.getValue(UserModel::class.java)
                Log.d("TAG", "${user?.email}/${user?.name}/${user?.admin}")
                PreferenceHelper.set("email",user?.email)
                PreferenceHelper.set("name",user?.name)
                PreferenceHelper.set("isAdmin",user?.admin)
                proceedToNextScreen(user?.admin ?: false)
            } else {
                Log.d("TAG", "${it.exception?.message}") //Never ignore potential errors!
            }
        }



//        dbRef.child(userId).get()
//            .addOnCompleteListener {
//                Toast.makeText(this, "Registered Successfully, please login now", Toast.LENGTH_LONG).show()
//                finish()
//
//            }.addOnFailureListener { err ->
//                err.printStackTrace()
//                Toast.makeText(this, "Error Occurred ${err.message}", Toast.LENGTH_LONG).show()
//            }
    }

    private fun proceedToNextScreen(isAdmin: Boolean) {
        val intent = Intent(this, if(isAdmin) AdminDashboardActivity::class.java else DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}