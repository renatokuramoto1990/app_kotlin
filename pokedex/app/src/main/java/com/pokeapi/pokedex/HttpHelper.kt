package com.pokeapi.pokedex

import android.util.Log
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.IOException

object HttpHelper {

    private val TAG = "HTTP_PokeAPI"
    private val LOG_ON = true
    val JSON = MediaType.parse("application/json; charset=utf-8")

    var client = OkHttpClient()

    fun get(url:String): String {
        val request = Request.Builder().url(url).get().build()
        Log.d(TAG, request.toString())
        return getJson(request)
    }

    fun post(url: String, json: String) : String {
        val body = RequestBody.create(JSON, json)
        val request = Request.Builder().url(url).post(body).build()
        return getJson(request)
    }

    private fun getJson(request: Request?): String {
        val response = client.newCall(request).execute()
        val body = response.body()
        if (body != null) {
            val json = body.string()
            return json
        }
        throw IOException("Erro na requisição")
    }

}