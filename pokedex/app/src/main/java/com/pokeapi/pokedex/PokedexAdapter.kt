package com.pokeapi.pokedex

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso



class PokedexAdapter(
    var pokemons: List<Pokedex>
): RecyclerView.Adapter<PokedexAdapter.MyPokemonsViewHolder>() {

    class MyPokemonsViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardImg : ImageView
        var cardProgress: ProgressBar
        var cardView: CardView

        init {
            cardNome = view.findViewById<TextView>(R.id.cardNome)
            cardImg = view.findViewById<ImageView>(R.id.cardImagem)
            cardProgress = view.findViewById<ProgressBar>(R.id.cardProgress)
            cardView = view.findViewById<CardView>(R.id.card_pokemons)

        }

    }

    override fun getItemCount() = this.pokemons.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPokemonsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.adapter_pokemon,
            parent,
            false
        )

        val holder = MyPokemonsViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: MyPokemonsViewHolder, position: Int) {
        val context = holder.itemView.context

        val pokemon = pokemons[position]

        Log.d("POK", pokemon.id)
        holder.cardNome.text = "${pokemon.name.toUpperCase()} - ${pokemon.id}"
        holder.cardProgress.visibility = View.VISIBLE


        Picasso.with(context).load(pokemon.image).fit().into(holder.cardImg,
            object : com.squareup.picasso.Callback {
                override fun onSuccess() {
                    holder.cardProgress.visibility = View.GONE
                }

                override fun onError() {
                    holder.cardProgress.visibility = View.GONE
                }
            })
    }
}