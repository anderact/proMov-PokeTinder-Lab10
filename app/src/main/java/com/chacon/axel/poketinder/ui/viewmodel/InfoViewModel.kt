package com.chacon.axel.poketinder.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chacon.axel.poketinder.data.network.FirebaseRemoteConfigRepository

class InfoViewModel : ViewModel() {

    private var firebaseRemoteConfigRepository = FirebaseRemoteConfigRepository()

    init {
        firebaseRemoteConfigRepository.init()
    }

    fun getUrlPokemon(): MutableLiveData<String> {
        return firebaseRemoteConfigRepository.getUrlPokemonLiveData
    }
}