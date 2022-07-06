package com.eventbrite.androidchallenge.data.events.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eventbrite.androidchallenge.data.events.model.EventsDto
import com.eventbrite.androidchallenge.data.events.network.EventsApiService
import com.eventbrite.androidchallenge.ui.events.EventsViewModel
import com.eventbrite.androidchallenge.ui.model.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response

@ExperimentalCoroutinesApi
class EventRepositoryImplTest {

    private val service = mock<EventsApiService>()
    private lateinit var eventRepositoryImpl: EventRepositoryImpl

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        eventRepositoryImpl = EventRepositoryImpl(service)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when api call isSuccessful then return State SUCCESS with an emptyList`() {
        runBlocking {
            val response = mock<Response<EventsDto>>()
            whenever(service.listOrganizerEvents()).thenReturn(response)
            whenever(response.isSuccessful).thenReturn(true)
            val flow = eventRepositoryImpl.getResults().toList()
            Assert.assertEquals(flow, listOf(State.SUCCESS(emptyList())))
        }
    }

    @Test
    fun `when api call fail then return objetc Error`() {
        runBlocking {
            val response = mock<Response<EventsDto>>()
            whenever(service.listOrganizerEvents()).thenReturn(response)
            whenever(response.isSuccessful).thenReturn(false)
            val flow = eventRepositoryImpl.getResults().toList()
            Assert.assertEquals(flow, listOf(State.ERROR))
        }
    }
}
