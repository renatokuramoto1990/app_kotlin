package com.pokeapi.pokedex

import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso

class PokemonAdapter (
    val pokemons: List<Pokemon>,
    val onClick: (Pokemon) -> Unit): RecyclerView.Adapter<PokemonAdapter.PokemonsViewHolder>() {

    // ViewHolder com os elemetos da tela
    class PokemonsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardImg: ImageView
        var cardProgress: ProgressBar
        var cardView: CardView

        init {
            cardNome = view.findViewById<TextView>(R.id.cardUrl)
            cardImg = view.findViewById<ImageView>(R.id.cardImgPokemon)
            cardProgress = view.findViewById<ProgressBar>(R.id.cardProgressBar)
            cardView = view.findViewById<CardView>(R.id.card_pokemons)

        }

    }

    override fun getItemCount() = this.pokemons.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_pokemon, parent, false)

        val holder = PokemonsViewHolder(view)
        return holder
    }


    override fun onBindViewHolder(holder: PokemonsViewHolder, position: Int) {
        val context = holder.itemView.context

        val pokemon = pokemons[position]

        holder.cardNome.text = pokemon.url

        // download da imagem
        Picasso.with(context).load(pokemon.url).fit().into(holder.cardImg,
            object : com.squareup.picasso.Callback {
                override fun onSuccess() {
                    holder.cardProgress.visibility = View.GONE
                }

                override fun onError() {
                    holder.cardProgress.visibility = View.GONE
                }
            })

        // adiciona evento de clique
        holder.itemView.setOnClickListener { onClick(pokemon) }
    }
}