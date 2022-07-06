package com.eventbrite.androidchallenge.data.events.model

import com.google.gson.annotations.SerializedName
import java.net.URL
import java.util.*

data class EventDto(
    val id: String,
    val name: String,
    val summary: String,
    @SerializedName("start_date")
    val startDate: Date,
    @SerializedName("start_time")
    val startTime: String,
    val image: ImageUrlDto?
)

data class EventsDto(
    val events: List<EventDto>
)

data class ImageUrlDto(
    val url: URL
)