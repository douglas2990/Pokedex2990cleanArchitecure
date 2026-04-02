package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.di

import android.app.Application
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        
        // Inicialização do Python
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
    }
}