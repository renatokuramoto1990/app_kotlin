package com.pokeapi.pokedex

import android.content.Context
import android.util.Log

object PokemonService {

    val host = "https://pokeapi.co/api/v2/"
    val TAG = "PokeAPI"


    fun getPokemons2 (context: Context): List<Pokemon> {
        val pokemons = mutableListOf<Pokemon>()

        for (i in 1..10) {
            val poke = Pokemon()
            poke.url = "Poke URL $i"
            poke.nome = "Pokemon $i"
            poke.foto = "https://cdn.bulbagarden.net/upload/thumb/7/7e/006Charizard.png/1200px-006Charizard.png"
            pokemons.add(poke)
        }

        return pokemons
    }

}