package com.example.antitheftapp.helper

import java.util.Date

data class QueueDetail(val userId: String = "",
                       val name: String = "",
                       val averageTime:Int = 0,
                       val dateTime : String = Date().toString()
)