package com.chacon.axel.poketinder.ui.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chacon.axel.poketinder.data.database.entities.MyPokemonEntity
import com.chacon.axel.poketinder.databinding.ItemPokemonBinding
import com.chacon.axel.poketinder.databinding.ItemPokemonSavedBinding

class MyPokemonsHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemPokemonSavedBinding.bind(view)

    fun bind(pokemon: MyPokemonEntity) {
        with(binding) {
            tvIndex.text = formatNumberTo3Digits(pokemon.idPokemon.toInt())
            tvName.text = pokemon.name
            tvType.text = if (pokemon.isLegendary) "Legendary" else "Normal"
            Glide
                .with(itemView)
                .load(pokemon.image)
                .into(binding.ivPokemonLogo)
        }
    }

    fun formatNumberTo3Digits(number: Int): String = "#${"%03d".format(number)}"
}