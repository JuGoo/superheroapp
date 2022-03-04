package com.ultimate.data.services

import com.ultimate.data.models.MarvelResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface CharactersService {

    @GET("/v1/public/characters")
    suspend fun fetchCharacters(
        @Query("ts") timestamp: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): MarvelResponse
}