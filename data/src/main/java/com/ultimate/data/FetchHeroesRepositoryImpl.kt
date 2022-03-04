package com.ultimate.data

import com.ultimate.data.api.MarvelApi
import com.ultimate.data.models.mapToHeroes
import com.ultimate.domain.models.Hero
import com.ultimate.domain.repositories.FetchHeroesRepository

internal class FetchHeroesRepositoryImpl(
    private val client: MarvelApi
) : FetchHeroesRepository {

    override suspend fun fetch(): List<Hero> = client.fetchCharacters().mapToHeroes()

}