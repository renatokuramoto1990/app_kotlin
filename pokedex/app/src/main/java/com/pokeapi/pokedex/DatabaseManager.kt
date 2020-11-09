package com.pokeapi.pokedex

import androidx.room.Room

object DatabaseManager {

    private var dbInstance: PokemonDatabase

    init {
        val context = PokeApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
            context,
            PokemonDatabase::class.java,
            "poke.sqlite"
        ).build()
    }

    fun getPokemonDAO(): PokemonDAO {
        return dbInstance.pokemonDAO()
    }
}