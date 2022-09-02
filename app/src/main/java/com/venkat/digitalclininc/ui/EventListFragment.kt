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
import com.venkat.digitalclininc.viewmodels.EventViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EventListFragment : Fragment() {
    private lateinit var bindingContext: EventListFragmentBinding

    //    private val eventViewModel: EventViewModel by viewModels()
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

        return bindingContext.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindingContext.eventListViewModel = eventListViewModel

        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
//        bindingContext.lifecycleOwner = viewLifecycleOwner

        eventListViewModel.getEvents()
        // TODO: expose the list of login Rx Observable and LiveData
        //  separately in the ViewModel and implement their bindings also separately as an exercise
        eventListViewModel.storageLiveData.observe(viewLifecycleOwner) { it ->
            lifecycleScope.launch {
                adapter = EventAdapter(it)
                bindingContext.eventList.adapter = adapter
            }

//            adapter.submitList(it) {
//                // At this point, the content should be drawn
//                activity?.reportFullyDrawn()
//            }
        }


        super.onViewCreated(view, savedInstanceState)
    }
}