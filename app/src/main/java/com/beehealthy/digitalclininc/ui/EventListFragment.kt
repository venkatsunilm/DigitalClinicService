package com.beehealthy.digitalclininc.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.beehealthy.digitalclinic.apiservice.api.mockdata.EventsMockList
import com.beehealthy.digitalclinic.apiservice.models.PatientEvent
import com.beehealthy.digitalclininc.adapter.EventAdapter
import com.beehealthy.digitalclininc.databinding.EventListFragmentBinding
import com.beehealthy.digitalclininc.digitalcliniccanalytics.ProjectAnalytics
import com.beehealthy.digitalclininc.helper.ApiResponseHelper
import com.beehealthy.digitalclininc.viewmodels.EventListViewModel
import com.beehealthy.digitalclininc.viewmodels.EventViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable

@AndroidEntryPoint
class EventListFragment : Fragment() {
    private lateinit var adapter: EventAdapter
    private lateinit var bindingContext: EventListFragmentBinding
    private val eventViewModel: EventViewModel by viewModels()
    private val eventListViewModel: EventListViewModel by viewModels()

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

        bindingContext = EventListFragmentBinding.inflate(inflater, container, false)

        eventListViewModel.getEvents().observe(viewLifecycleOwner) { it ->
            // TODO: This TEMPORARY if condition will be removed once the API is ready
            if (it.statusCode == 200) {
                context?.let { it1 ->
                    ApiResponseHelper.handleApiResponse(
                        it1.applicationContext,
                        it
                    ) { items: List<PatientEvent>? ->
                        if (items != null) {
                            adapter = EventAdapter(items, eventViewModel)
                        }
                    }
                }
            } else {
                Observable
                    .fromCallable { EventsMockList.getEventsMockList().value }
                    .subscribe { item: List<PatientEvent>? ->
                        if (item != null) {
                            adapter = EventAdapter(item.toList(), eventViewModel)
                        }
                    }
                bindingContext.eventList.adapter = adapter
            }
        }
        return bindingContext.root
    }
}