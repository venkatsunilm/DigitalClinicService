package com.beehealthy.digitalclininc.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beehealthy.digitalclininc.R
import com.beehealthy.digitalclininc.viewmodels.AppointmentDetailsViewModel

class AppointmentDetailsFragment : Fragment() {

    // TODO: UNDER CONSTRUCTION
    companion object {
        fun newInstance() = AppointmentDetailsFragment()
    }

    private lateinit var viewModel: AppointmentDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.appointment_details_fragment, container, false)
    }
}