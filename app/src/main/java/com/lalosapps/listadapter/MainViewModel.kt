package com.lalosapps.listadapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _cards = MutableLiveData<List<CardItem>>()
    val cards: LiveData<List<CardItem>> = _cards

    var isInsertion = false
        private set

    init {
        _cards.value = CardProvider.cards
    }

    fun toggleFavorite(cardItem: CardItem) {
        _cards.value = CardProvider.toggleFavorite(cardItem)
    }

    fun deleteCard(cardItem: CardItem) {
        _cards.value = CardProvider.deleteCard(cardItem)
    }

    fun insertCard() {
        isInsertion = true
        _cards.value = CardProvider.insertCard()
    }

    fun onScrollDone() {
        isInsertion = false
    }
}