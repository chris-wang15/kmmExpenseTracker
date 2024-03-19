package com.tools.records.android

import android.app.Application
import com.tools.records.data.di.DatabaseModel

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        APP = this
        DatabaseModel.initDatabase(this)
    }

    companion object {
        lateinit var APP: Application
    }
}