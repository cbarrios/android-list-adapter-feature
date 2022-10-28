package com.lalosapps.listadapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

class MainViewModel : ViewModel() {

    val cards = CardProvider.cardsFlow.asLiveData()

    var isInsertion = false
        private set

    fun toggleFavorite(cardItem: CardItem) {
        CardProvider.toggleFavorite(cardItem.id)
    }

    fun deleteCard(cardItem: CardItem) {
        CardProvider.deleteCard(cardItem)
    }

    fun insertCard() {
        isInsertion = true
        CardProvider.insertCard()
    }

    fun onScrollDone() {
        isInsertion = false
    }
}