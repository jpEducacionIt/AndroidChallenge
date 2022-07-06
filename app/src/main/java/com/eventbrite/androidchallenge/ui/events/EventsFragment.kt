package com.eventbrite.androidchallenge.ui.events

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eventbrite.androidchallenge.R
import com.eventbrite.androidchallenge.data.events.repository.State
import com.eventbrite.androidchallenge.databinding.EventsFragmentBinding
import com.eventbrite.androidchallenge.ui.showSnackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EventsFragment : Fragment(R.layout.events_fragment) {

    @Inject
    lateinit var adapterEvents: EventsAdapter
    private val viewModel by viewModels<EventsViewModel>()
    lateinit var binding: EventsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = EventsFragmentBinding.bind(view)
        binding.recyclerviewEvents.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = adapterEvents
        }

        viewModel.results.observe(viewLifecycleOwner) { result ->
            when (result) {
                State.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.root.showSnackbar(R.string.error_loading_data, R.string.retry) {
                        viewModel.getEvents()
                    }
                }

                is State.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    adapterEvents.submitList(result.events)
                }

                State.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
                else -> {
                    State.START
                }
            }
        }
        viewModel.getEvents()

        adapterEvents.onItemClickListener = {
            Toast.makeText(activity, it.name, Toast.LENGTH_SHORT).show()
        }
    }
}
