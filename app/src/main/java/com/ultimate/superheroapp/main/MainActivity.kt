package com.ultimate.superheroapp.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.ultimate.presentation.factories.PresentationFactory
import com.ultimate.superheroapp.R
import com.ultimate.superheroapp.application.AppModule
import com.ultimate.superheroapp.application.SuperHeroApplication
import com.ultimate.superheroapp.application.appModule
import com.ultimate.superheroapp.databinding.ActivityMainBinding
import com.ultimate.superheroapp.home.HomeFragment
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {
    private val viewModelFactory: ViewModelProvider.Factory by appModule { PresentationFactory() }
    private val fragmentFactory: MainFragmentFactory by lazy { createMainFragmentFactory() }

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)
        binding = createBinding()
        pushHomeFragment()
    }

    private fun pushHomeFragment() = supportFragmentManager.pushFragment(HomeFragment::class, "home", null)

    private fun createMainFragmentFactory() = MainFragmentFactory(viewModelFactory)

    private fun createBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater).also { binding ->
        setContentView(binding.root)
    }
}

private fun <T : Fragment> FragmentManager.pushFragment(clazz: KClass<T>, id: String, args: Bundle?) = commit {
    addToBackStack(id)
    replace(R.id.fragmentContainer, clazz.java, args, id)
}