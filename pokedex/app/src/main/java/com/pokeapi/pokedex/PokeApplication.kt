package com.pokeapi.pokedex

import android.app.Application
import java.lang.IllegalStateException

class PokeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: PokeApplication? = null

        fun getInstance(): PokeApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configurar application no manifest")
            }
            return appInstance!!
        }
    }
}