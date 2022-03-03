package com.ultimate.presentation.features.home

import androidx.lifecycle.ViewModel
import com.ultimate.domain.usecases.FetchHeroesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeViewModel(
    private val fetchHeroesUseCase: FetchHeroesUseCase
): ViewModel() {

    fun start(): Flow<Unit> = flow {
        val result = fetchHeroesUseCase.run()
        emit(Unit)
    }
}