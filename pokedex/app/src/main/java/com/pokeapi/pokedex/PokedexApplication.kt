package com.pokeapi.pokedex

import android.app.Application
import java.lang.IllegalStateException

class PokedexApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: PokedexApplication?  = null
        fun getInstance(): PokedexApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configurar application no Android Manifest")
            }
            return appInstance!!
        }
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}