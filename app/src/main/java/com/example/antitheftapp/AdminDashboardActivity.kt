package com.example.antitheftapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.antitheftapp.helper.PreferenceHelper
import com.example.antitheftapp.helper.QueueDetail
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter


class AdminDashboardActivity : AppCompatActivity() {

    private lateinit var mainLayout: ConstraintLayout
    private lateinit var emptyLayout: ConstraintLayout
    private lateinit var textViewCurrentToken: TextView
    private lateinit var textViewYoursToken: TextView
    private lateinit var textViewName: TextView
    private lateinit var imageViewQR: ImageView

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

        mainLayout = findViewById(R.id.mainLayout)
        emptyLayout = findViewById(R.id.emptyLayout)
        emptyLayout.visibility = View.VISIBLE

        imageViewQR = findViewById(R.id.imageViewQR)
        textViewName = findViewById(R.id.textViewName)
        textViewCurrentToken = findViewById(R.id.textViewCurrentToken)
        textViewYoursToken = findViewById(R.id.textViewYoursToken)

        getActiveQueue()




        findViewById<Button>(R.id.buttonCreateQueue).setOnClickListener {
            startActivity(Intent(this,AddQueueActivity::class.java))
        }

        logout()

    }

    private fun getActiveQueue() {
        val ref = Firebase.database.reference.child("Queues")
        val userId = FirebaseAuth.getInstance().uid!!
        val query: Query = ref.orderByChild("userId").equalTo(userId)

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.value == null){
                    mainLayout.visibility = View.GONE
                    emptyLayout.visibility = View.VISIBLE
                }else{
                    for (childSnapshot in dataSnapshot.children) {
                        val queueDetail = childSnapshot.getValue(QueueDetail::class.java)
//                    val id = userSnapshot.child("id").getValue(String::class.java)
//                    val name = userSnapshot.child("name").getValue(String::class.java)
//                    val avgTime = userSnapshot.child("averageTime").getValue(Int::class.java)
                        // Do something with the retrieved data
                        loadData(queueDetail)
                        println("Name: ${queueDetail?.name}, AvgTime: ${queueDetail?.averageTime}")
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
                println("Error: ${databaseError.message}")
                Utils.showToast(this@AdminDashboardActivity,"Error Ocurred")
            }
        })

//        ref.get().addOnCompleteListener {
//            if (it.isSuccessful) {
//                val user = it.result.getValue(UserModel::class.java)
//                Log.d("TAG", "${user?.email}/${user?.name}/${user?.admin}")
//                PreferenceHelper.set("email",user?.email)
//                PreferenceHelper.set("name",user?.name)
//                PreferenceHelper.set("isAdmin",user?.admin)
//            } else {
//                Log.d("TAG", "${it.exception?.message}") //Never ignore potential errors!
//            }
//        }


    }

    private fun loadData(queueDetail: QueueDetail?) {
        textViewName.text = queueDetail?.name
        textViewCurrentToken.text = "0"
        textViewYoursToken.text = "0"

        mainLayout.visibility = View.VISIBLE
        emptyLayout.visibility = View.GONE


        imageViewQR.setImageBitmap(getQrCodeBitmap(queueDetail?.name,queueDetail?.id))

        //textViewCurrentToken.text = queueDetail?.
    }

    private fun logout() {
        findViewById<Button>(R.id.buttonLogout).setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            PreferenceHelper.init(this)
            PreferenceHelper.set("userId", null)
            PreferenceHelper.set("isAdmin", false)
            finish()
        }
    }

    fun getQrCodeBitmap(name: String?, id: String?): Bitmap {
        val size = 512 //pixels
        val qrCodeContent = "Name:$name:Id:$id"
        val hints = hashMapOf<EncodeHintType, Int>().also { it[EncodeHintType.MARGIN] = 1 } // Make the QR code buffer border narrower
        val bits = QRCodeWriter().encode(qrCodeContent, BarcodeFormat.QR_CODE, size, size, hints)
        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
            for (x in 0 until size) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        }
    }
}