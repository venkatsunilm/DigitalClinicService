package com.venkat.digitalclininc.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.venkat.digitalclininc.adapter.EventAdapter
import com.venkat.digitalclininc.databinding.EventListFragmentBinding
import com.venkat.digitalclininc.viewmodels.EventListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EventListFragment : Fragment() {
    private lateinit var bindingContext: EventListFragmentBinding
    private val eventListViewModel: EventListViewModel by viewModels()
    private lateinit var adapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bindingContext = EventListFragmentBinding.inflate(inflater, container, false)

        adapter = EventAdapter(listOf())
        bindingContext.eventList.adapter = adapter

        eventListViewModel.getEvents()
        eventListViewModel.storageLiveData.observe(viewLifecycleOwner) { it ->
            lifecycleScope.launch {
                bindingContext.eventList.adapter = EventAdapter(it)
            }
        }

        return bindingContext.root
    }
}