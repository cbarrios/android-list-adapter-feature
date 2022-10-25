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

        adapter = CardAdapter().apply {
            submitList(CardProvider.cards)
        }
        binding.recycler.adapter = adapter
    }
}