package com.beehealthy.digitalclininc.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.beehealthy.digitalclininc.R
import com.beehealthy.digitalclininc.adapter.*
import com.beehealthy.digitalclininc.databinding.PatientHomeViewPagerFragmentBinding
import com.beehealthy.digitalclininc.digitalcliniccanalytics.ProjectAnalytics
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IndexOutOfBoundsException

@AndroidEntryPoint
class PatientHomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        object {}.javaClass.enclosingMethod?.name?.let {
            activity?.let { context ->
                ProjectAnalytics.getInstance(context)
                    .sendEvent(object {}.javaClass.enclosingClass.simpleName, it)
            }
        }

        // get the binding context from the auto generated FragmentPatientHomeViewPagerBinding
        val binding = PatientHomeViewPagerFragmentBinding.inflate(inflater, container, false)
        val tabLayout = binding.homeTabs
        val viewPager = binding.homeViewPager

        // set the adapter before attaching the tab Layout to ViewPager2
        // viewPager.adapter = PatientHomePagerAdapter(this) OR using scope operator
        PatientHomePagerAdapter(this).also { viewPager.adapter = it }

        // Link the TabLayout and the ViewPager2 together.
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            //TODO: Un-block this method call when right drawables are replaced
            // tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.homeToolbar)
        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            EVENTS_PAGE -> R.drawable.events_tab_selector
            PRESCRIPTION_PAGE -> R.drawable.prescription_tab_selector
            VACCINATIONS_PAGE -> R.drawable.events_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            EVENTS_PAGE -> "Events"
            PRESCRIPTION_PAGE -> "Prescriptions"
            VACCINATIONS_PAGE -> "Vaccinations"
            else -> null
        }
    }
}