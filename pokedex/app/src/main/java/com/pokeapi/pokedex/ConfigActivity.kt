package com.pokeapi.pokedex

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_config.*
import kotlinx.android.synthetic.main.activity_config.inputNewLogin
import kotlinx.android.synthetic.main.activity_config.inputNewPassword
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.*

class ConfigActivity : AppCompatActivity() {
    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_config)

        setSupportActionBar(toolbar_view)
        supportActionBar?.title = "Configurações da Pokedex"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        btnSalvarSenha()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item?.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun btnSalvarSenha(){
        btnSalvarSenha.setOnClickListener {
            val usuario = inputNewLogin.text.toString()
            val senha = inputNewPassword.text.toString()

            if (usuario == "" || senha == "") {
                Toast.makeText(this, "Preencha o usuário e senha", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Agora seu usuário é $usuario, e a sua senha é $senha", Toast.LENGTH_LONG).show()
                finish()
            }
        }

    }
}