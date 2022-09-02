package com.venkat.digitalclininc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.venkat.digitalclinic.apiservice.models.PatientPrescription
import com.venkat.digitalclininc.databinding.PrescriptionFragmentBinding
import com.venkat.digitalclininc.viewmodels.PrescriptionViewModel

class PrescriptionAdapter(
    private var values: List<PatientPrescription>,
    private val prescriptionViewModel: PrescriptionViewModel
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
            prescriptionViewModel.validityDate = item.patientEndDate
            prescriptionViewModel.patientName = item.patientFirstName
            prescriptionViewModel.referralType = item.referenceHeaderType
            prescriptionViewModel.doctorName = item.doctorFullName
            prescriptionViewModel.writtenDate = item.writtenDate

            binding.apply {
                viewmodel = prescriptionViewModel
                executePendingBindings()
            }
        }
    }

    fun submitList(newData: List<PatientPrescription>) {
        values = ArrayList()
        values = newData
        notifyDataSetChanged()
    }

}