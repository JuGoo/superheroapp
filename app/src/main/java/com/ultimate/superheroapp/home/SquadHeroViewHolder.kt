package com.ultimate.superheroapp.home

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ultimate.presentation.models.HeroItem
import com.ultimate.superheroapp.databinding.SquadHeroItemBinding

class SquadHeroViewHolder(private val binding: SquadHeroItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: HeroItem) = binding.run {
        Glide.with(thumbnail.context)
            .load(item.imageUrl)
            .circleCrop()
            .into(thumbnail)
        title.text = item.name
    }
}