package com.eventbrite.androidchallenge.data.events.repository

import kotlinx.coroutines.flow.Flow

interface EventRepository {
    suspend fun getResults(): Flow<State>
}