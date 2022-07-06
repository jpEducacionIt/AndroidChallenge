package com.eventbrite.androidchallenge.ui.model

import com.eventbrite.androidchallenge.data.events.model.EventDto
import com.eventbrite.androidchallenge.data.events.model.ImageUrlDto

fun ImageUrlDto.toImageUrl() = ImageUrl(url)

fun EventDto.toEvent() = Event(
    id, name, summary, startDate, startTime, image?.toImageUrl()
)
