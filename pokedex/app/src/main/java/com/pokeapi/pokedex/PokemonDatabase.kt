package com.pokeapi.pokedex

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Pokemon::class), version = 1)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun pokemonDAO(): PokemonDAO


}