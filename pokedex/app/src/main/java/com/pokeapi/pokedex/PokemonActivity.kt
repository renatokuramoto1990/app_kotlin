package com.pokeapi.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pokemon.*
import kotlinx.android.synthetic.main.toolbar.*

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

        supportActionBar?.title = pokemon?.nome
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nomePokemonInfo.text = pokemon?.nome
        Picasso.with(this).load(pokemon?.foto).fit().into(imagemPokemonInfo,
            object : com.squareup.picasso.Callback {
                override fun onSuccess() {}

                override fun onError() {}
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item?.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}