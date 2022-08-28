package com.beehealthy.digitalclininc.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.beehealthy.digitalclinic.apiservice.api.mockdata.EventsMockList
import com.beehealthy.digitalclinic.apiservice.models.PatientPrescription
import com.beehealthy.digitalclininc.adapter.PrescriptionAdapter
import com.beehealthy.digitalclininc.constants.ApplicationConstants
import com.beehealthy.digitalclininc.databinding.PrescriptionListFragmentBinding
import com.beehealthy.digitalclininc.digitalcliniccanalytics.ProjectAnalytics
import com.beehealthy.digitalclininc.helper.ApiResponseHelper
import com.beehealthy.digitalclininc.viewmodels.PrescriptionListViewModel
import com.beehealthy.digitalclininc.viewmodels.PrescriptionViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable

@AndroidEntryPoint
class PrescriptionListFragment: Fragment(){

    private lateinit var adapter: PrescriptionAdapter
    private lateinit var bindingContext: PrescriptionListFragmentBinding
    private val prescriptionViewModel: PrescriptionViewModel by viewModels()
    private val prescriptionListViewModel: PrescriptionListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        object {}.javaClass.enclosingMethod?.name?.let {
            ProjectAnalytics.getInstance(ApplicationConstants.applicationContext)
                .sendEvent(object {}.javaClass.enclosingClass.simpleName, it)
        }

        bindingContext = PrescriptionListFragmentBinding.inflate(inflater, container, false)

        prescriptionListViewModel.getPrescriptions().observe(viewLifecycleOwner) { it ->
            // TODO: This TEMPORARY if condition will be removed once the API is ready
            if (it.statusCode == 200) {
                context?.let { it1 ->
                    ApiResponseHelper.handleApiResponse(
                        it1.applicationContext,
                        it
                    ) { items: List<PatientPrescription>? ->
                        if (items != null) {
                            adapter = PrescriptionAdapter(items, prescriptionViewModel)
//                            adapter.submitList(items)
                        }
                    }
                }
            } else {
                Observable
                    .fromCallable { EventsMockList.getPrescriptionMockList().value }
                    .subscribe { item: List<PatientPrescription>? ->
                        if (item != null) {
                            adapter = PrescriptionAdapter(item.toList(), prescriptionViewModel)
                        }
                    }
                bindingContext.prescriptionList.adapter = adapter
            }
        }
        return bindingContext.root
    }

}