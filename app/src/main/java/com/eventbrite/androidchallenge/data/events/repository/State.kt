package com.eventbrite.androidchallenge.data.events.repository

import com.eventbrite.androidchallenge.ui.model.Event

sealed class State {
    object ERROR: State()
    object START: State()
    object LOADING: State()
    data class SUCCESS(val events: List<Event>): State()
}
