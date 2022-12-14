package com.lalosapps.listadapter

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

object CardProvider {

    private var cards = listOf(
        CardItem(
            1,
            "Title 1",
            "Description 1",
            false
        ),
        CardItem(
            2,
            "Title 2",
            "Description 2",
            false
        ),
        CardItem(
            3,
            "Title 3",
            "Description 3",
            false
        ),
        CardItem(
            4,
            "Title 4",
            "Description 4",
            false
        ),
        CardItem(
            5,
            "Title 5",
            "Description 5",
            false
        ),
        CardItem(
            6,
            "Title 6",
            "Description 6",
            false
        ),
        CardItem(
            7,
            "Title 7",
            "Description 7",
            false
        ),
        CardItem(
            8,
            "Title 8",
            "Description 8",
            false
        ),
        CardItem(
            9,
            "Title 9",
            "Description 9",
            false
        ),
        CardItem(
            10,
            "Title 10",
            "Description 10",
            false
        )
    )
    
    private val _cardsFlow = MutableStateFlow(cards)
    val cardsFlow = _cardsFlow.asStateFlow()

    fun toggleFavorite(cardId: Int): List<CardItem> {
        cards = cards.map {
            if (cardId == it.id) {
                it.copy(favorite = !it.favorite)
            } else it
        }
        _cardsFlow.value = cards
        return cards
    }

    fun deleteCard(card: CardItem): List<CardItem> {
        cards = cards.filterNot { card == it }
        _cardsFlow.value = cards
        return cards
    }

    fun insertCard(): List<CardItem> {
        val id = Random.nextInt()
        val newCard = CardItem(id, "Title $id", "Description $id", false)
        cards = cards + newCard
        _cardsFlow.value = cards
        return cards
    }
}