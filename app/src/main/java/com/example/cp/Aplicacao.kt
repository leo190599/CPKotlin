package com.example.cp

import android.app.Application

class Aplicacao:Application() {
    companion object {
        var ponteiroDeEmail=""
        lateinit var bancoDeDados: AppDatabase
    }
    override fun onCreate() {
        super.onCreate()
        bancoDeDados = AppDatabase.getDatabase(this)
    }
}