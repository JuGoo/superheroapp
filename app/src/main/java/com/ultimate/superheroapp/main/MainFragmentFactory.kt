package com.ultimate.superheroapp.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.ultimate.superheroapp.home.HomeFragment

class MainFragmentFactory(
    private val viewModelFactory: ViewModelProvider.Factory
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (classLoader.loadClass(className)) {
            HomeFragment::class.java -> HomeFragment(viewModelFactory)
            else -> super.instantiate(classLoader, className)
        }
}