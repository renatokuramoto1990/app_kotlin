package com.pokeapi.pokedex

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_app.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*
import kotlin.random.Random

class AppActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    private var pokemon = listOf<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_app)

        val args:Bundle? = intent.extras
        val user = args?.getString("username")

        Toast.makeText(context, "Bem vindo $user!", Toast.LENGTH_LONG).show()

        setSupportActionBar(toolbar_view)
        supportActionBar?.title = "Pokedex"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()

        recyclerPokedex?.layoutManager = LinearLayoutManager(context)
        recyclerPokedex?.itemAnimator = DefaultItemAnimator()
        recyclerPokedex?.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        taskPokemom()
    }

    fun enviaNotificacao(pokemon: Pokemon) {
        val intent = Intent(this, PokemonActivity::class.java)
        intent.putExtra("pokemon", pokemon)
        NotificationUtil.create(this, 1, intent, "Pokedex", "Nova iteração em  ${pokemon.name}")
    }

    fun taskPokemom() {
        Thread {
            this.pokemon = PokemonService.getPokemons(context)
            runOnUiThread {
                recyclerPokedex?.adapter = PokemonAdapter(pokemon) { onClickPokemon(it) }
                enviaNotificacao(this.pokemon.get(0))
            }
        }.start()
    }

    fun taskSearchPokemon(url: String) {
        Thread {
            this.pokemon = PokemonService.getSearchPokemon(context, url)
            runOnUiThread {
                recyclerPokedex?.adapter = PokemonAdapter(pokemon) { onClickPokemon(it) }
            }
        }.start()
    }

    fun onClickPokemon(pokemon: Pokemon) {
        Toast.makeText(context, "Clicou no Pokemon ${pokemon.name}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, PokemonActivity::class.java)
        intent.putExtra("pokemon", pokemon)
        startActivity(intent)
    }

    private fun configuraMenuLateral() {
        var toogle = ActionBarDrawerToggle(this, layoutMenuLateral, toolbar_view, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()
        menu_lateral.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_pokedex -> {
                val intent = Intent(context, MyPokedexActivity::class.java)
                startActivityForResult(intent, 1)
            }
            R.id.nav_mapas -> {
                val intent = Intent(context, MapasActivity::class.java)
                startActivityForResult(intent, 1)
            }
            R.id.nav_sair -> {
                finish()
            }
        }
        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText == "") {
                    return false
                } else if (newText > "721") {
                    return false
                }
                val url = "https://pokeapi.co/api/v2/pokemon/${newText}"
                taskSearchPokemon(url)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                if (query > "721") {
                    return false
                }
                val url = "https://pokeapi.co/api/v2/pokemon/${query}"
                taskSearchPokemon(url)
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item?.itemId

        if (id == R.id.action_buscar) {
            Toast.makeText(context, "Clicou em Buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            taskPokemom()
        } else if (id == R.id.action_config) {
            val intent = Intent(context, ConfigActivity::class.java)
            startActivityForResult(intent, 1)
        }
        return super.onOptionsItemSelected(item)
    }
}