package com.eventbrite.androidchallenge.data.events.network

import com.eventbrite.androidchallenge.data.events.model.EventsDto
import retrofit2.Response
import retrofit2.http.GET

interface EventsApiService {

    @GET("destination/organizers/22699500963/events/?expand=image&token=QGSCFRJYOKAA7IDDPMON")
    suspend fun listOrganizerEvents(): Response<EventsDto>
}
