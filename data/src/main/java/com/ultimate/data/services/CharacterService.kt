package com.ultimate.data.services

import com.ultimate.data.models.MarvelResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface CharacterService {

    @GET("/v1/public/characters/{id}")
    suspend fun fetchCharacter(
        @Path("id") id: String,
        @Query("ts") timestamp: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): MarvelResponse
}