package com.ultimate.domain.repositories

import com.ultimate.domain.models.Hero

interface FetchHeroRepository {
    suspend fun fetch(id: Int): Hero
}