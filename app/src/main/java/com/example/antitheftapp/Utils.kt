package com.example.antitheftapp

import android.content.Context
import android.widget.Toast

class Utils {
    companion object{
        val IS_DEMO = true

        fun showToast(context: Context, message: String) {
            Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT).show()
        }
    }
}