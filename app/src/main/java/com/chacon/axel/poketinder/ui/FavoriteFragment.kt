package com.chacon.axel.poketinder.ui

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.chacon.axel.poketinder.data.database.entities.MyPokemonEntity
import com.chacon.axel.poketinder.databinding.FragmentFavoriteBinding
import com.chacon.axel.poketinder.ui.adapter.MyPokemonsAdapter
import com.chacon.axel.poketinder.ui.viewmodel.FavoriteViewModel

class FavoriteFragment: BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate){

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private var listMyPokemon = mutableListOf<MyPokemonEntity>()

    private val adapter by lazy { MyPokemonsAdapter(listMyPokemon) }

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)

        favoriteViewModel.getMyPokemon(requireContext())

        binding.rvPokemons.adapter = adapter

        favoriteViewModel.myPokemonList.observe(this) {
            listMyPokemon.addAll(it)
            adapter.notifyDataSetChanged()
        }

        binding.floatingActionDelete.setOnClickListener {
            favoriteViewModel.deleteAllPokemon(requireContext())
        }
    }
}