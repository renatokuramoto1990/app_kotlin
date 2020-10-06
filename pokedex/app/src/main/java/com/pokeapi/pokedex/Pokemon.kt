package com.pokeapi.pokedex

import com.google.gson.GsonBuilder
import java.io.Serializable

class Pokemon : Serializable{
    var url = ""

    override fun toString(): String {
        return "Pokemon(url='$url')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}