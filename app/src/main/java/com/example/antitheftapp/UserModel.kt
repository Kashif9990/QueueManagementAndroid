package com.example.antitheftapp

data class UserModel(val id:String = "",
                     val email: String = "",
                     val name: String = "",
                     val mobile:String = "",
                     val age:Int = 0,
                     val isAdmin:Boolean = false)
