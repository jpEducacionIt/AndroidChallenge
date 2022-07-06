package com.eventbrite.androidchallenge.ui.events

import androidx.lifecycle.*
import com.eventbrite.androidchallenge.data.events.repository.EventRepository
import com.eventbrite.androidchallenge.data.events.repository.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel
@Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {
    private var mResults = MutableLiveData<State>()
    val results: LiveData<State>
        get() = mResults

    fun getEvents(){
        viewModelScope.launch {
            mResults.value = State.LOADING
            eventRepository.getResults().collect {
                mResults.value = it
            }
        }
    }
}
