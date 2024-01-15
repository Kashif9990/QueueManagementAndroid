package com.example.antitheftapp.application

import android.app.Application
import com.example.antitheftapp.helper.PreferenceHelper

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        PreferenceHelper.init(this)
    }
}