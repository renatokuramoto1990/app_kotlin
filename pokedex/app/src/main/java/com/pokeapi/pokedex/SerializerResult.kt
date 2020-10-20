package com.pokeapi.pokedex

import java.io.Serializable

class SerializerResult : Serializable {
    val results = ""

    override fun toString(): String {
        return "SerializerResult(results='$results')"
    }
}