package com.pokeapi.pokedex

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_app.layoutMenuLateral
import kotlinx.android.synthetic.main.activity_my_pokedex.*
import kotlinx.android.synthetic.main.toolbar.*

class MyPokedexActivity : AppCompatActivity() {

    private val context: Context get() = this
    private var pokemon = listOf<Pokedex>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_my_pokedex)

        setSupportActionBar(toolbar_view)
        supportActionBar?.title = "Minha Pokedex"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerMyPokedex?.layoutManager = LinearLayoutManager(context)
        recyclerMyPokedex?.itemAnimator = DefaultItemAnimator()
        recyclerMyPokedex?.setHasFixedSize(true)

    }

    override fun onResume() {
        super.onResume()
        taskPokemom()
    }

    fun taskPokemom() {
        Thread {
            this.pokemon = PokemonService.getPokemonsPokedex(context)
            runOnUiThread {
                recyclerMyPokedex?.adapter = PokedexAdapter(pokemon)
            }
        }.start()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item?.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
