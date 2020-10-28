package com.pokeapi.pokedex

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PokemonDAO {
    @Query("SELECT * FROM  pokemons where id = :id")
    fun getById(id: Long): Pokemon?

    @Query("SELECT * FROM pokemons")
    fun findAll(): List<Pokemon>

    @Insert
    fun insert(pokemon: Pokemon)

    @Delete
    fun delete(pokemon: Pokemon)
}