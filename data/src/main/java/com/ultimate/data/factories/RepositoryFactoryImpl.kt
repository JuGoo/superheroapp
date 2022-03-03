package com.ultimate.data.factories

import com.ultimate.data.FetchHeroesRepositoryImpl
import com.ultimate.data.api.MarvelApi
import com.ultimate.data.api.MarvelApiImpl
import com.ultimate.domain.factories.RepositoryFactory
import com.ultimate.domain.repositories.FetchHeroesRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RepositoryFactoryImpl(
    privateKey: String,
    publicKey: String
) : RepositoryFactory {

    private val marvelApi = createMarvelApi(privateKey, publicKey)

    override fun provideFetchHeroesRepository(): FetchHeroesRepository =
        FetchHeroesRepositoryImpl(marvelApi)
}

private fun createRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(createHttpClient())
    .addConverterFactory(createConverterFactory())
    .build()

private fun createHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .readTimeout(READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
    .addInterceptor(createLoggingInterceptor())
    .build()

private fun createLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

private const val READ_TIMEOUT_IN_SECONDS: Long = 60

private fun createConverterFactory(): Converter.Factory = GsonConverterFactory.create()

private fun createMarvelApi(privateKey: String, publicKey: String): MarvelApi = MarvelApiImpl(
    privateKey = privateKey,
    publicKey = publicKey,
    retrofit = createRetrofit("https://gateway.marvel.com")
)