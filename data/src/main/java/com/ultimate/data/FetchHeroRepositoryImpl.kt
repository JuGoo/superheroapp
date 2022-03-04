package com.ultimate.data

import com.ultimate.data.api.MarvelApi
import com.ultimate.data.models.mapToHeroes
import com.ultimate.domain.models.Hero
import com.ultimate.domain.repositories.FetchHeroRepository

internal class FetchHeroRepositoryImpl(
    private val client: MarvelApi
) : FetchHeroRepository {
    override suspend fun fetch(id: Int): Hero =
        client.fetchCharacter(id).mapToHeroes().first()
}
