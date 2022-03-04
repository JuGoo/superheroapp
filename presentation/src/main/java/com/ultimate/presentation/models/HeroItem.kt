package com.ultimate.presentation.models

data class HeroItem(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val description: String,
    val isRecruited: Boolean
)