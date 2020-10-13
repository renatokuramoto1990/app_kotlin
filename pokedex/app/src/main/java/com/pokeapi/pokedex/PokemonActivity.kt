package com.pokeapi.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pokemon.*
import kotlinx.android.synthetic.main.toolbar.*

class PokemonActivity : AppCompatActivity() {
    var pokemon: Pokemon? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        pokemon = intent.getSerializableExtra("pokemon") as Pokemon

        setSupportActionBar(toolbar_view)

        supportActionBar?.title = pokemon?.nome

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nomePokemon.text = pokemon?.nome
        Picasso.with(this).load(pokemon?.foto).fit().into(imagemPokemon,
            object : com.squareup.picasso.Callback {
                override fun onSuccess() {}
                override fun onError() {}
            })
    }
}