package com.lalosapps.listadapter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.lalosapps.listadapter.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CardAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CardAdapter(
            onCardClicked = {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ID, it.id)
                startActivity(intent)
            },
            onCardLongClicked = {
                viewModel.deleteCard(it)
            },
            onFavoriteToggled = {
                viewModel.toggleFavorite(it)
            }
        )
        binding.recycler.adapter = adapter

        binding.addCardFab.setOnClickListener {
            viewModel.insertCard()
        }

        setupObservers()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.cards.flowWithLifecycle(lifecycle).collect { cards ->
                adapter.submitList(cards)
                if (viewModel.isInsertion) {
                    binding.recycler.smoothScrollToPosition(cards.lastIndex)
                    viewModel.onScrollDone()
                }
            }
        }
    }
}