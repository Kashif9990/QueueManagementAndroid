package com.example.antitheftapp.helper

data class UserModel(val id:String = "",
                     val email: String = "",
                     val name: String = "",
                     val mobile:String = "",
                     val age:Int = 0,
                     val address:String = "",
                     val admin:Boolean = false)
