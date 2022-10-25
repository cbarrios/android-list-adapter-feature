package com.lalosapps.listadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lalosapps.listadapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CardAdapter(
            onCardClicked = {
                binding.root.snack(it.title)
            },
            onCardLongClicked = {
                val newList = CardProvider.deleteCard(it)
                adapter.submitList(newList)
            },
            onFavoriteToggled = {
                val updatedList = CardProvider.toggleFavorite(it)
                adapter.submitList(updatedList)
            }
        ).apply {
            submitList(CardProvider.cards)
        }
        binding.recycler.adapter = adapter

        binding.addCardFab.setOnClickListener {
            val newList = CardProvider.insertCard()
            adapter.submitList(newList)
            binding.recycler.smoothScrollToPosition(newList.lastIndex)
        }
    }
}