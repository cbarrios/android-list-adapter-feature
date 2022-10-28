package com.lalosapps.listadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.lalosapps.listadapter.databinding.ActivityDetailBinding
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "id"
    }

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(EXTRA_ID, -1)

        binding.toggleButton.setOnClickListener {
            viewModel.toggleFavorite(id)
        }

        lifecycleScope.launch {
            viewModel.observeCard(id).flowWithLifecycle(lifecycle).collect { card ->
                card?.let {
                    binding.cardItem.text = it.toString()
                    binding.root.snack("Card ${it.id}")
                }
            }
        }
    }
}