package com.ultimate.domain.repositories

interface SquadRepository {

    suspend fun add(id: Int)
    suspend fun remove(id: Int)
    suspend fun contains(id: Int): Boolean
    suspend fun getAll(): List<Int>
}