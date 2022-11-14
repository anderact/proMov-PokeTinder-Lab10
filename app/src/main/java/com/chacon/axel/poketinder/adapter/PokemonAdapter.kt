package com.chacon.axel.poketinder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.chacon.axel.poketinder.R
import com.chacon.axel.poketinder.data.model.PokemonResponse
import com.chacon.axel.poketinder.databinding.ItemPokemonBinding
import javax.security.auth.callback.Callback

class PokemonAdapter(
    var list: List<PokemonResponse>,
    val callback: Callback
    ) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = parent.inflate(R.layout.item_pokemon)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int){
            val item = list[position]
            holder.bind(item)
        }

        override fun getItemCount(): Int = list.size

        inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

            private val binding = ItemPokemonBinding.bind(view)

            fun bind(pokemon: PokemonResponse) {
                with(binding) {
                    root.setOnClickListener {
                        callback.onClickPokemonInformation(pokemon)
                    }
                    tvName.text = pokemon.name
                    Glide
                        .with(itemView)
                        .load(pokemon.getPokemonImage())
                        .into(binding.ivPokemon)
                }
            }
        }

        interface Callback {
            fun onClickPokemonInformation(pokemon: PokemonResponse)
        }
    }

    fun ViewGroup.inflate(layoutRes: Int) : View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)