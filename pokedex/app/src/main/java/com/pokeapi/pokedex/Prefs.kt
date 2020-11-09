package com.pokeapi.pokedex

import android.content.SharedPreferences

object Prefs {
    private fun prefs() : SharedPreferences {
        var contexto = PokeApplication.getInstance().applicationContext
        return contexto.getSharedPreferences("Poke", 0)
    }

    fun getString(flag: String): String? {
        return prefs().getString(flag, "")
    }

    fun setString(flag: String, valor: String) {
        prefs().edit().putString(flag, valor).apply()
    }

    fun getBoolean(flag: String): Boolean {
        return prefs().getBoolean(flag, false)
    }

    fun setBoolean(flag: String, valor: Boolean) {
        prefs().edit().putBoolean(flag, valor).apply()
    }
}