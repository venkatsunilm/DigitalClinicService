package com.venkat.digitalclininc.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.venkat.digitalclininc.R
import com.venkat.digitalclininc.viewmodels.AppointmentDetailsViewModel

// TODO: UNDER CONSTRUCTION
class AppointmentDetailsFragment : Fragment() {

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