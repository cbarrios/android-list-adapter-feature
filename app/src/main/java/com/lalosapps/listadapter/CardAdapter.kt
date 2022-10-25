package com.lalosapps.listadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lalosapps.listadapter.databinding.CardItemBinding

class CardAdapter(
    val onCardClicked: (CardItem) -> Unit,
    val onFavoriteToggled: (CardItem) -> Unit
) : ListAdapter<CardItem, CardAdapter.CardViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = getItem(position)
        with(holder) {
            bind(card)
            binding.apply {
                root.setOnClickListener { onCardClicked(card) }
                icon.setOnClickListener { onFavoriteToggled(card) }
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<CardItem>() {
        override fun areItemsTheSame(oldItem: CardItem, newItem: CardItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CardItem, newItem: CardItem): Boolean {
            return oldItem == newItem
        }
    }

    class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CardItemBinding.bind(view)

        fun bind(card: CardItem) {
            binding.apply {
                title.text = card.title
                description.text = card.description
                val resource =
                    if (card.favorite) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
                icon.setImageResource(resource)
            }
        }
    }
}