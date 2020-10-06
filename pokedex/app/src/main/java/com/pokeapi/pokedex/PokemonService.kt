package com.pokeapi.pokedex

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PokemonService {

    val host = "https://pokeapi.co/api/v2/"
    val TAG = "PokeAPI"

    fun getPokemons (context: Context): List<Pokemon> {
        var pokemon = ArrayList<Pokemon>()
        val url = "$host/pokemon"
        val json = HttpHelper.get(url)
        pokemon = parserJson(json)
        Log.d(TAG, pokemon.toString())
        return pokemon
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}