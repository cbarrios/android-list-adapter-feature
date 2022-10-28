package com.lalosapps.listadapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.map

class DetailViewModel : ViewModel() {

    fun observeCard(id: Int) =
        CardProvider.cardsFlow.map { list -> list.firstOrNull { it.id == id } }.asLiveData()

    fun toggleFavorite(cardId: Int) {
        CardProvider.toggleFavorite(cardId)
    }
}