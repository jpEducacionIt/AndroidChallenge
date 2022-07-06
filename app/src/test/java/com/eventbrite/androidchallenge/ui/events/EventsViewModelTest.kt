package com.eventbrite.androidchallenge.ui.events

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eventbrite.androidchallenge.data.events.repository.EventRepository
import com.eventbrite.androidchallenge.data.events.repository.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.*
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class EventsViewModelTest {

    private val eventRepository = mock<EventRepository>()
    private lateinit var eventsViewModel: EventsViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        eventsViewModel = EventsViewModel(eventRepository)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Success state works`() = runBlocking {
        whenever(eventRepository.getResults()).thenReturn(flow { emit(State.SUCCESS(emptyList())) })
        eventsViewModel.getEvents()
        Assert.assertEquals(State.SUCCESS(emptyList()), eventsViewModel.results.value)
    }

    @Test
    fun `Error state works`() = runBlocking {
        whenever(eventRepository.getResults()).thenReturn(flow { emit(State.ERROR) })
        eventsViewModel.getEvents()
        Assert.assertEquals(State.ERROR, eventsViewModel.results.value)
    }
}
