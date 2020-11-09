package com.pokeapi.pokedex

import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {

    private val context: Context get() = this
    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        Prefs.setString("FB_TOKEN", token!!)
    }

    override fun onMessageReceived(mensagemRemota: RemoteMessage?) {
        if (mensagemRemota?.notification != null) {
            val titulo = mensagemRemota?.notification?.title
            val corpo = mensagemRemota?.notification?.body

            showNotification(mensagemRemota)
        }
    }

    private fun showNotification(mensagemRemota: RemoteMessage) {

        val intent = Intent(this, PokemonActivity::class.java)

        val titulo = mensagemRemota?.notification?.title?: getString(R.string.app_name)
        var mensagem = mensagemRemota?.notification?.body!!

        if(mensagemRemota?.data.isNotEmpty()) {
            val pokemonid = mensagemRemota.data.get("id")
            mensagem += ""
            val url = "https://pokeapi.co/api/v2/pokemon/${pokemonid}"

            val pokemon = PokemonService.getSearchPokemon(this, url)
            intent.putExtra("pokemon", pokemon[0])
        }
        NotificationUtil.create(this, 1, intent, titulo, mensagem)
    }
}
