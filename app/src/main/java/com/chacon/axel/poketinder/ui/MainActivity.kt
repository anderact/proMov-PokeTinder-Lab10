package com.chacon.axel.poketinder.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import com.chacon.axel.poketinder.adapter.PokemonAdapter
import com.chacon.axel.poketinder.data.model.PokemonResponse
import com.chacon.axel.poketinder.data.network.PokemonApi
import com.chacon.axel.poketinder.databinding.ActivityMainBinding
import com.chacon.axel.poketinder.ui.viewmodel.MainViewModel
import com.yuyakaido.android.cardstackview.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    CardStackListener,
    PokemonAdapter.Callback {

    private val listPokemon: List<PokemonResponse> = emptyList()

    private val adapter by lazy { PokemonAdapter(listPokemon, this) }

    private val manager by lazy { CardStackLayoutManager(this, this) }

    private val viewModel by lazy { MainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeTinderCard()
        viewModel.pokemonList.observe(this) {
            adapter.list = it
            adapter.notifyDataSetChanged()
        }

        viewModel.isLoading.observe(this){
            binding.progressBar.isVisible = it
        }
    }

    private fun initializeTinderCard() {
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollVertical(true)
        manager.setCanScrollHorizontal(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())

        binding.rvTinderPokemon.layoutManager = manager

        binding.rvTinderPokemon.adapter = adapter

        binding.rvTinderPokemon.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {

    }

    override fun onCardRewound() {

    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardDisappeared(view: View?, position: Int) {

    }

    override fun onClickPokemonInformation(pokemon: PokemonResponse) {
        Toast.makeText(this, "Pokemon: ${pokemon.name}", Toast.LENGTH_SHORT).show()
    }
}