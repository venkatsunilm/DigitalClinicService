package com.venkat.digitalclininc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.venkat.digitalclinic.apiservice.models.PatientEvent
import com.venkat.digitalclininc.databinding.EventFragmentBinding
import com.venkat.digitalclininc.viewmodels.EventViewModel

class EventAdapter(
    private var values: List<PatientEvent>
) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            EventFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.updateBindValues(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(
        private val binding: EventFragmentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun updateBindValues(item: PatientEvent) {
            with(binding) {
                eventViewModel = EventViewModel(item)
                executePendingBindings()
            }
        }
    }
}
