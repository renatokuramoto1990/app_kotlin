package com.pokeapi.pokedex

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PokemonDAO {
    @Query("SELECT * FROM  pokedex where id = :id")
    fun getById(id: Long): Pokedex?

    @Query("SELECT * FROM pokedex")
    fun findAll(): List<Pokedex>

    @Insert
    fun insert(pokemon: Pokedex)

    @Delete
    fun delete(pokemon: Pokedex)
}