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
    class PokemonsViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardUrl: TextView
        val cardImg : ImageView
        var cardProgress: ProgressBar
        var cardView: CardView

        init {
            cardNome = view.findViewById<TextView>(R.id.cardNome)
            cardUrl = view.findViewById<TextView>(R.id.cardUrl)
            cardImg = view.findViewById<ImageView>(R.id.cardImgPokemon)
            cardProgress = view.findViewById<ProgressBar>(R.id.cardProgressBar)
            cardView = view.findViewById<CardView>(R.id.card_pokemons)

        }

    }

    // Quantidade de disciplinas na lista

    override fun getItemCount() = this.pokemons.size

    // inflar layout do adapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonsViewHolder {
        // infla view no adapter
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_pokemon, parent, false)

        // retornar ViewHolder
        val holder = PokemonsViewHolder(view)
        return holder
    }

    // bind para atualizar Views com os dados

    override fun onBindViewHolder(holder: PokemonsViewHolder, position: Int) {
        val context = holder.itemView.context

        // recuperar objeto disciplina
        val disciplina = pokemons[position]

        // atualizar dados de disciplina

        holder.cardNome.text = disciplina.nome
        holder.cardProgress.visibility = View.VISIBLE

        // download da imagem
        Picasso.with(context).load(disciplina.foto).fit().into(holder.cardImg,
            object: com.squareup.picasso.Callback{
                override fun onSuccess() {
                    holder.cardProgress.visibility = View.GONE
                }

                override fun onError() {
                    holder.cardProgress.visibility = View.GONE
                }
            })

        // adiciona evento de clique
        holder.itemView.setOnClickListener {onClick(disciplina)}
    }
}