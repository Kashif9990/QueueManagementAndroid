package com.example.antitheftapp.helper

import java.util.Date

data class QueueDetail(val id: String = "",
                       val userId: String = "",
                       val name: String = "",
                       val averageTime:Int = 0,
                       val dateTime : String = Date().toString()
)

data class QueueSubscription(
    val id: String? = "",
    val userId: String = "",
    val queueName: String? = "",
    val averageTime: Int? = 0,
    val dateTime: String = Date().toString()
)