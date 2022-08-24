package com.beehealthy.digitalclininc.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.beehealthy.digitalclinic.apiservice.models.PatientEvent
import com.beehealthy.digitalclininc.adapter.EventAdapter
import com.beehealthy.digitalclininc.databinding.EventListFragmentBinding
import com.beehealthy.digitalclininc.viewmodels.EventListViewModel
import com.beehealthy.digitalclininc.viewmodels.EventViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable

// generated a hilt component
@AndroidEntryPoint
class EventListFragment : Fragment() {

    private lateinit var adapter: EventAdapter
    // will initialize this as necessary
    private lateinit var bindingContext: EventListFragmentBinding

     private val eventListViewModel: EventListViewModel by viewModels()
    // other way to get a view model
    // private lateinit var eventListViewModel: EventListViewModel
    // or lazy initialization
//    private val eventListViewModel: EventListViewModel by lazy {
//        ViewModelProvider(this)[EventListViewModel::class.java]
//    }

    private val eventViewModel: EventViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingContext = EventListFragmentBinding.inflate(inflater, container, false)
        // getting the view model with viewModelProvider
        // eventListViewModel = ViewModelProvider(this)[EventListViewModel::class.java]
        // It takes time to fetch the data from the service
        Observable
            .fromCallable { eventListViewModel.getEvents().value }
            .subscribe { item: List<PatientEvent>? ->
                if (item != null) {
                    adapter = EventAdapter(item.toList(), eventViewModel)
                }
            }

        bindingContext.eventList.adapter = adapter

        eventListViewModel.getEvents().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return bindingContext.root
    }
}