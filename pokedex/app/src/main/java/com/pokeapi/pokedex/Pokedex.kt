package com.pokeapi.pokedex

import java.io.Serializable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokedex")
class Pokedex : Serializable{

    @PrimaryKey
    var id = ""
    var name = ""
    var abilities = ""
    var types = ""
    var weight = ""
    var image = ""

    override fun toString(): String {
        return "Pokemon(name='$name')"
    }


}