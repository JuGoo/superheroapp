package com.ultimate.data.api

import com.ultimate.data.models.CharacterResponse
import com.ultimate.data.models.MarvelResponse
import com.ultimate.data.services.CharacterService
import com.ultimate.data.services.CharactersService
import retrofit2.Retrofit
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

internal interface MarvelApi {

    suspend fun fetchCharacters(): MarvelResponse
    suspend fun fetchCharacter(id: Int): MarvelResponse
}

internal class MarvelApiImpl(
    privateKey: String,
    private val publicKey: String,
    private val retrofit: Retrofit
) : MarvelApi {

    private val hash: String = "$TIMESTAMP$privateKey$publicKey".md5()

    override suspend fun fetchCharacters(): MarvelResponse =
        retrofit.create(CharactersService::class.java).fetchCharacters(
            timestamp = TIMESTAMP,
            apikey = publicKey,
            hash = hash
        )

    override suspend fun fetchCharacter(id: Int): MarvelResponse =
        retrofit.create(CharacterService::class.java).fetchCharacter(
            id = id.toString(),
            timestamp = TIMESTAMP,
            apikey = publicKey,
            hash = hash
        )

    companion object {
        private const val TIMESTAMP: Int = 1
    }
}

private fun String.md5(): String {
    val messageDigest = MessageDigest.getInstance("MD5")
    val bigInt = BigInteger(1, messageDigest.digest(toByteArray(UTF_8)))
    return String.format("%032x", bigInt)
}
