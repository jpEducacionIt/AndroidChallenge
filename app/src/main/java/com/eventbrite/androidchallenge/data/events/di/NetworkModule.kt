package com.eventbrite.androidchallenge.data.events.di

import com.eventbrite.androidchallenge.data.events.network.EventsApiService
import com.eventbrite.androidchallenge.data.events.repository.EventRepository
import com.eventbrite.androidchallenge.data.events.repository.EventRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): EventsApiService {
        return Retrofit.Builder()
            .baseUrl("https://www.evbqaapi.com/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EventsApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideEventRepository(service: EventsApiService): EventRepository = EventRepositoryImpl(service)
}
