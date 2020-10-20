package com.pokeapi.pokedex

import java.io.Serializable

class Pokemon : Serializable{
    var name = ""
    var id = ""
    var abilities = ""
    var types = ""
    var weight = ""
    var image = ""
    var next = ""

    override fun toString(): String {
        return "Pokemon(name='$name')"
    }


}