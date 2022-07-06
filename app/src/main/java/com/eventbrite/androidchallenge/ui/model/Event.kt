package com.eventbrite.androidchallenge.ui.model


import java.net.URL
import java.util.*

data class Event(
    val id: String,
    val name: String,
    val summary: String,
    val startDate: Date,
    val startTime: String,
    val image: ImageUrl?
)

data class Events(
    val events: List<Event>
)

data class ImageUrl(
    val url: URL
)