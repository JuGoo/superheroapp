package com.ultimate.superheroapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ultimate.presentation.models.HeroItem
import com.ultimate.superheroapp.R
import com.ultimate.superheroapp.databinding.SquadHeroItemBinding

class SquadRecyclerAdapter(
    private val select: (Int) -> Unit
) : RecyclerView.Adapter<SquadHeroViewHolder>() {
    private var items: List<HeroItem> = emptyList()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SquadHeroViewHolder =
        SquadHeroViewHolder(
            SquadHeroItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.squad_hero_item, parent, false)
            )
        )

    override fun getItemId(position: Int): Long = items[position].hashCode().toLong()

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: SquadHeroViewHolder, position: Int) {
        val item = items[position]
        viewHolder.bind(item)
        viewHolder.itemView.setOnClickListener { select(item.id) }
    }

    fun updateItems(heroes: List<HeroItem>) {
        val itemsDiffUtilCallback = HeroItemsDiffUtilCallback(newItems = heroes, oldItems = items)
        items = heroes
        DiffUtil.calculateDiff(itemsDiffUtilCallback).dispatchUpdatesTo(this)
    }
}
