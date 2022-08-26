package com.beehealthy.digitalclininc.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.beehealthy.digitalclinic.apiservice.models.PatientEvent
import com.beehealthy.digitalclininc.adapter.EventAdapter
import com.beehealthy.digitalclininc.databinding.EventListFragmentBinding
import com.beehealthy.digitalclininc.digitalcliniccanalytics.ProjectAnalytics
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

    private val eventViewModel: EventViewModel by viewModels()
    private val eventListViewModel: EventListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        object {}.javaClass.enclosingMethod?.name?.let {
            activity?.let { context ->
                ProjectAnalytics.getInstance(context.applicationContext)
                    .sendEvent(object {}.javaClass.enclosingClass.simpleName, it)
            }
        }

        bindingContext = EventListFragmentBinding.inflate(inflater, container, false)

        Observable
            .fromCallable { activity?.let { eventListViewModel.getEvents(it.application).value } }
            .subscribe { item: List<PatientEvent>? ->
                if (item != null) {
                    adapter = EventAdapter(item.toList(), eventViewModel)
                }
            }

        bindingContext.eventList.adapter = adapter

        activity?.let {
            eventListViewModel.getEvents(it.application).observe(viewLifecycleOwner) {
                activity?.let { context ->
                    ProjectAnalytics.getInstance(context.applicationContext)
                        .sendEvent(
                            object {}.javaClass.enclosingClass.simpleName,
                            "Data change triggered"
                        )
                }
                adapter.submitList(it)
            }
        }

        return bindingContext.root
    }
}