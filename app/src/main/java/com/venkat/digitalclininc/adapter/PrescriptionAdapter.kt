package com.venkat.digitalclininc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.venkat.digitalclinic.apiservice.models.PatientPrescription
import com.venkat.digitalclininc.databinding.PrescriptionFragmentBinding
import com.venkat.digitalclininc.viewmodels.EventViewModel
import com.venkat.digitalclininc.viewmodels.PrescriptionViewModel

class PrescriptionAdapter(
    private var values: List<PatientPrescription>
) : RecyclerView.Adapter<PrescriptionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            PrescriptionFragmentBinding.inflate(
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
        private val binding: PrescriptionFragmentBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun updateBindValues(item: PatientPrescription) {
            with(binding) {
                prescriptionViewModel = PrescriptionViewModel(item)
            }
        }
    }
}