package com.venkat.digitalclininc.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.venkat.digitalclinic.apiservice.api.mockdata.EventsMockList
import com.venkat.digitalclinic.apiservice.models.PatientEvent
import com.venkat.digitalclininc.adapter.EventAdapter
import com.venkat.digitalclininc.databinding.EventListFragmentBinding
import com.venkat.digitalclininc.digitalcliniccanalytics.ProjectAnalytics
import com.venkat.digitalclininc.viewmodels.EventListViewModel
import com.venkat.digitalclininc.viewmodels.EventViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.Job

@AndroidEntryPoint
class EventListFragment : Fragment() {
    private lateinit var bindingContext: EventListFragmentBinding
    private val eventViewModel: EventViewModel by viewModels()
    private val eventListViewModel: EventListViewModel by viewModels()
    private lateinit var adapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        object {}.javaClass.enclosingMethod?.name?.let {
            this.activity?.let { it1 ->
                ProjectAnalytics.getInstance(it1.applicationContext)
                    .sendEvent(object {}.javaClass.enclosingClass.simpleName, it)
            }
        }
        adapter = EventAdapter(listOf(), eventViewModel)
        bindingContext = EventListFragmentBinding.inflate(inflater, container, false)
        bindingContext.eventList.adapter = adapter

        eventListViewModel.getEvents().observe(viewLifecycleOwner) { it ->
            // TODO: This TEMPORARY if condition will be removed once the API is ready
            if (it.statusCode == 200) {
                it.data?.let { items -> adapter = EventAdapter(items, eventViewModel) }
            } else {
                // TODO: Remove this observable
                Observable
                    .fromCallable { EventsMockList.getEventsMockList().value }
                    .subscribe { item: List<PatientEvent>? ->
                        if (item != null) {
                            adapter = EventAdapter(item, eventViewModel)
                        }
                    }
                bindingContext.eventList.adapter = adapter
            }
        }
        return bindingContext.root
    }

}