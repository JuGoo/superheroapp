package com.ultimate.presentation.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ultimate.data.factories.RepositoryFactoryImpl
import com.ultimate.domain.factories.RepositoryFactory
import com.ultimate.domain.factories.UseCaseFactory
import com.ultimate.presentation.BuildConfig
import com.ultimate.presentation.features.detail.DetailViewModel
import com.ultimate.presentation.features.home.HomeViewModel

class PresentationFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val result: Any = when (modelClass) {
            HomeViewModel::class.java -> createHomeViewModel()
            DetailViewModel::class.java -> createDetailViewModel()
            else -> throw IllegalStateException("Unsupported view model: $modelClass")
        }
        return checkNotNull(modelClass.cast(result))
    }

    private fun createHomeViewModel() = HomeViewModel(
        fetchHeroesUseCase = createUseCaseFactory().provideFetchHeroesUseCase()
    )

    private fun createDetailViewModel() = DetailViewModel()

    private fun createUseCaseFactory() = UseCaseFactory(createRepositoryFactory())

    private fun createRepositoryFactory(): RepositoryFactory = RepositoryFactoryImpl(
        privateKey = BuildConfig.PRIVATE_KEY,
        publicKey = BuildConfig.PUBLIC_KEY
    )
}