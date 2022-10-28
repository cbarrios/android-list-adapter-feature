package com.lalosapps.listadapter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.map

class DetailViewModel : ViewModel() {

    fun observeCard(id: Int) =
        CardProvider.cardsFlow.map { list -> list.firstOrNull { it.id == id } }

    fun toggleFavorite(cardId: Int) {
        CardProvider.toggleFavorite(cardId)
    }
}