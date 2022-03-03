package com.ultimate.data.models

import com.ultimate.domain.models.Hero
import kotlinx.serialization.Serializable

@Serializable
internal data class CharactersResponse(val data: DataResponse)

@Serializable
internal data class DataResponse(val results: List<CharacterResponse>)

@Serializable
internal data class CharacterResponse(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: ThumbnailResponse
)

@Serializable
internal data class ThumbnailResponse(val path: String)

internal fun CharactersResponse.mapToHeroes(): List<Hero> = data.results.map { it.mapHero() }

private fun CharacterResponse.mapHero(): Hero = Hero(name, thumbnail.path)
