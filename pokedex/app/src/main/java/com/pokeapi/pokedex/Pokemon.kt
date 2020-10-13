package com.pokeapi.pokedex

import java.io.Serializable

class Pokemon : Serializable{
    var url = ""
    var nome = ""
    var foto = ""

    override fun toString(): String {
        return "Pokemon(url='$nome')"
    }
}