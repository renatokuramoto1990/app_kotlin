package com.pokeapi.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_app.*
import kotlinx.android.synthetic.main.activity_pokemon.*
import kotlin.random.Random
import kotlin.random.nextInt

class PokemonActivity : AppCompatActivity() {
    var pokemon: Pokemon? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_pokemon)

        pokemon = intent.getSerializableExtra("pokemon") as Pokemon

        supportActionBar?.title = pokemon?.name?.toUpperCase()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = pokemon?.id
        val nome = pokemon?.name?.toUpperCase()
        val habilidades = pokemon?.abilities?.capitalize()
        val tipos = pokemon?.types?.capitalize()
        val peso = pokemon?.weight?.capitalize()
        val imagem = pokemon?.image

        nomePokemon.text = "Pokemon - ${nome}"
        habilidadesPokemon.text = "Habilidades\n*${habilidades}"
        tiposPokemon.text = "Tipo do Pokemon\n*${tipos}"
        pesoPokemon.text = "Peso\n*${peso}"
        Picasso.with(this).load(imagem).fit().into(imagemPokemonInfo,
            object : com.squareup.picasso.Callback {
                override fun onSuccess() {}

                override fun onError() {}
            })

        inputPokemonName.setOnClickListener(){
            inputPokemonName.setText("")
        }

        btnSalvarPokemon.setOnClickListener {
            var nomePokemon = ""
            val name = inputPokemonName.text.toString()
            if (name == "Apelido do Pokemon") {
                nomePokemon = nome!!
            } else {
                nomePokemon = name
            }
            val poke = Pokedex()
            poke.id = Random.nextInt(1, 99999999999.toInt()).toString()
            poke.name = nomePokemon.toString()
            poke.abilities = habilidades.toString()
            poke.types = tipos.toString()
            poke.weight = peso.toString()
            poke.image = imagem.toString()

            Log.d("POK", nomePokemon)

            Thread {
                PokemonService.save(poke)
            }.start()

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item?.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}