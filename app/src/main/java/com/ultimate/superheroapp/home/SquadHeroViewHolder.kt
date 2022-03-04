package com.ultimate.superheroapp.home

import androidx.recyclerview.widget.RecyclerView
import com.ultimate.presentation.models.HeroItem
import com.ultimate.superheroapp.databinding.SquadHeroItemBinding
import com.ultimate.superheroapp.utils.imageloader.ImageLoader

class SquadHeroViewHolder(private val binding: SquadHeroItemBinding, private val imageLoader: ImageLoader) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: HeroItem) = binding.run {
        imageLoader.process(item.imageUrl, thumbnail)
        title.text = item.name
    }

    fun unBind() = binding.run {
        imageLoader.clearLoadings(thumbnail)
    }
}