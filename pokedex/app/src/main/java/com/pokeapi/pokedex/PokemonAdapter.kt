package com.pokeapi.pokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import android.content.Context
import android.util.Log
import kotlinx.android.synthetic.main.activity_app.*

class PokemonAdapter(
    var pokemons: List<Pokemon>,
    val onClick: (Pokemon) -> Unit
): RecyclerView.Adapter<PokemonAdapter.PokemonsViewHolder>() {

    private var last = ""
    class PokemonsViewHolder(view: View): RecyclerView.ViewHolder(view) {
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.adapter_pokemon,
            parent,
            false
        )

        val holder = PokemonsViewHolder(view)
        return holder
    }


    override fun onBindViewHolder(holder: PokemonsViewHolder, position: Int) {
        val context = holder.itemView.context

        Log.d("ADAPTER", last)
        if(pokemons[position].next != null && pokemons[position].next != last) {
            Thread {
                this.pokemons += PokemonService.getMorePokemons(context, pokemons[position].next)
                last = pokemons[position].next
            }.start()
        }

        val pokemon = pokemons[position]

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

        holder.itemView.setOnClickListener {onClick(pokemon)}
    }
}