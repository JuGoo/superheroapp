package com.ultimate.superheroapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ultimate.presentation.models.HeroItem
import com.ultimate.superheroapp.R
import com.ultimate.superheroapp.databinding.HeroItemBinding

class HeroesRecyclerAdapter(
    private val select: (Int) -> Unit
) : RecyclerView.Adapter<HeroViewHolder>() {
    private var items: List<HeroItem> = emptyList()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder =
        HeroViewHolder(
            HeroItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.hero_item, parent, false)
            )
        )

    override fun getItemId(position: Int): Long = items[position].hashCode().toLong()

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: HeroViewHolder, position: Int) {
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

internal class HeroItemsDiffUtilCallback(
    private val newItems: List<HeroItem>,
    private val oldItems: List<HeroItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition].id == newItems[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition] == newItems[newItemPosition]
}