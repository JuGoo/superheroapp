package com.ultimate.data

import com.ultimate.domain.repositories.SquadRepository

class SquadRepositoryImpl: SquadRepository {

    private val squad: MutableList<Int> = mutableListOf()

    override suspend fun add(id: Int) {
        squad.add(id)
    }

    override suspend fun remove(id: Int) {
        squad.remove(id)
    }

    override suspend fun contains(id: Int): Boolean = squad.contains(id)

    override suspend fun getAll(): List<Int> = squad
}