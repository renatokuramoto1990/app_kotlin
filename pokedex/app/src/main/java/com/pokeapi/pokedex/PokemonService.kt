package com.pokeapi.pokedex

import android.content.Context
import android.util.Log
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.Exception

object PokemonService {

    val host = "https://pokeapi.co/api/v2"
    val TAG = "PokeAPI"
    val TAG_RESULT = "RESULT"

    fun getPokemonsPokedex (context: Context): List<Pokedex> {
        val dao = DatabaseManager.getPokemonDAO()
        return dao.findAll()
    }


    fun getPokemons (context: Context): List<Pokemon> {
        val pokemons = mutableListOf<Pokemon>()

        val url = "$host/pokemon"
        val json = HttpHelper.get(url)

        val response = JsonParser().parse(json)
        val results = response.asJsonObject["results"]
        val next = response.asJsonObject["next"].asString

        for (i in results.asJsonArray) {
            val url = i.asJsonObject["url"].asString
            val response = HttpHelper.get(url)
            val json = JsonParser().parse(response)
            val jsonSprites = json.asJsonObject["sprites"].asJsonObject["other"].asJsonObject["official-artwork"]
            var abilities = ""
            var types = ""
            val poke = Pokemon()
            poke.name = json.asJsonObject["name"].asString
            poke.id = json.asJsonObject["id"].asString
            for (j in json.asJsonObject["abilities"].asJsonArray) {
                abilities += "${j.asJsonObject["ability"].asJsonObject["name"].asString} "
            }
            for (type in json.asJsonObject["types"].asJsonArray) {
                types += "${type.asJsonObject["type"].asJsonObject["name"].asString} "
            }
            poke.abilities = abilities
            poke.types = types
            poke.weight = json.asJsonObject["weight"].asString
            poke.image = jsonSprites.asJsonObject["front_default"].asString
            poke.next = next
            pokemons.add((poke))
        }
        return pokemons
    }

    fun getMorePokemons (context: Context, url: String): List<Pokemon> {
        val pokemons = mutableListOf<Pokemon>()

        val json = HttpHelper.get(url)

        val response = JsonParser().parse(json)
        val results = response.asJsonObject["results"]
        val next = response.asJsonObject["next"].asString

        for (i in results.asJsonArray) {
            val url = i.asJsonObject["url"].asString
            val response = HttpHelper.get(url)
            val json = JsonParser().parse(response)
            val jsonSprites = json.asJsonObject["sprites"].asJsonObject["other"].asJsonObject["official-artwork"]
            var abilities = ""
            var types = ""
            val poke = Pokemon()
            poke.name = json.asJsonObject["name"].asString
            poke.id = json.asJsonObject["id"].asString
            for (j in json.asJsonObject["abilities"].asJsonArray) {
                abilities += "${j.asJsonObject["ability"].asJsonObject["name"].asString} "
            }
            for (type in json.asJsonObject["types"].asJsonArray) {
                types += "${type.asJsonObject["type"].asJsonObject["name"].asString} "
            }
            poke.abilities = abilities
            poke.types = types
            poke.weight = json.asJsonObject["weight"].asString
            poke.image = jsonSprites.asJsonObject["front_default"].asString
            poke.next = next
            pokemons.add((poke))
        }
        return pokemons
    }

    fun getSearchPokemon (context: Context, url: String): List<Pokemon> {
        val pokemons = mutableListOf<Pokemon>()

        val json = HttpHelper.get(url)
        var response: JsonElement
        try {
            response = JsonParser().parse(json)
        }catch (e: Exception) {
            return pokemons
        }

        if (!response.isJsonNull) {
            val jsonSprites = response.asJsonObject["sprites"].asJsonObject["other"].asJsonObject["official-artwork"]
            var abilities = ""
            var types = ""
            val poke = Pokemon()
            poke.name = response.asJsonObject["name"].asString
            poke.id = response.asJsonObject["id"].asString
            for (j in response.asJsonObject["abilities"].asJsonArray) {
                abilities += "${j.asJsonObject["ability"].asJsonObject["name"].asString} "
            }
            for (type in response.asJsonObject["types"].asJsonArray) {
                types += "${type.asJsonObject["type"].asJsonObject["name"].asString} "
            }
            poke.abilities = abilities
            poke.types = types
            poke.weight = response.asJsonObject["weight"].asString
            poke.image = jsonSprites.asJsonObject["front_default"].asString
            pokemons.add((poke))
        }
        return pokemons
    }


    fun save(pokemon: Pokemon) {
        HttpHelper.post("$host/pokemon", GsonBuilder().create().toJson(pokemon))
    }

    inline fun <reified T> parserJson(json: String) : T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }

    inline fun <reified T> parseResult(json: String) : List<SerializerResult> {
        return Gson().fromJson(json, Array<SerializerResult>::class.java).toList()
    }
}