package com.beehealthy.digitalclininc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beehealthy.digitalclinic.apiservice.models.PatientEvent
import com.beehealthy.digitalclininc.databinding.EventFragmentBinding
import com.beehealthy.digitalclininc.viewmodels.EventViewModel

class EventAdapter(
    private var values: List<PatientEvent>,
    private val eventViewModel: EventViewModel
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
            eventViewModel.validityDate = item.ValidityDate
            eventViewModel.patientName = item.patientFirstName
            eventViewModel.referralType = item.referenceHeaderType
            eventViewModel.doctorName = item.doctorFullName
            eventViewModel.writtenDate = item.writtenDate

            binding.viewmodel = eventViewModel
        }
    }

    fun submitList(newData: List<PatientEvent>) {
        values = ArrayList()
        values = newData
        notifyDataSetChanged()
    }

}