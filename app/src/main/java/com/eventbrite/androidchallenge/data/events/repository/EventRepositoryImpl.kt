package com.eventbrite.androidchallenge.data.events.repository

import android.util.Log
import com.eventbrite.androidchallenge.data.events.network.EventsApiService
import com.eventbrite.androidchallenge.ui.model.toEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EventRepositoryImpl(
    private val service: EventsApiService
) : EventRepository {
    override suspend fun getResults(): Flow<State> = flow {
        try {
            val response = service.listOrganizerEvents()

            if (response.isSuccessful) {
                emit(State.SUCCESS(
                    response.body()?.events?.map { it.toEvent() } ?: emptyList()))
            }else {
                emit(State.ERROR)
            }
        } catch(exception: Exception) {
            emit(State.ERROR)
        }
    }
}
