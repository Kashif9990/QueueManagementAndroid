package com.example.antitheftapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class JoinQueueActivity : AppCompatActivity() {

    private lateinit var buttonJoin: Button
    private lateinit var buttonQrCode: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queue_join)


        buttonJoin = findViewById<Button>(R.id.buttonJoin)
        buttonQrCode = findViewById<Button>(R.id.buttonLoadWithQR)

        buttonJoin.setOnClickListener {
            //Todo Add Join Queue Logic here
            finish()
        }

        buttonQrCode.setOnClickListener {
            //Todo Add Join Queue Logic here
            Utils.showToast(this,"Load QR")
            finish()
        }



    }
}
