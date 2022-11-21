package com.chacon.axel.poketinder.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B: ViewBinding>(val bindingFactory: (LayoutInflater) -> B) : Fragment() {

    lateinit var binding: B

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        binding = bindingFactory(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }
}