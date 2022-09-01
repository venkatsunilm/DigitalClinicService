package com.venkat.digitalclininc.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.venkat.digitalclininc.R
import com.venkat.digitalclininc.adapter.EVENTS_PAGE
import com.venkat.digitalclininc.adapter.PRESCRIPTION_PAGE
import com.venkat.digitalclininc.adapter.PatientHomePagerAdapter
import com.venkat.digitalclininc.adapter.VACCINATIONS_PAGE
import com.venkat.digitalclininc.databinding.PatientHomeViewPagerFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PatientHomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = PatientHomeViewPagerFragmentBinding.inflate(inflater, container, false)
        val tabLayout = binding.homeTabs
        val viewPager = binding.homeViewPager

        // set the adapter before attaching the tab Layout to ViewPager2
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
            EVENTS_PAGE -> "Appointments"
            PRESCRIPTION_PAGE -> "Prescriptions"
            VACCINATIONS_PAGE -> "Health History"
            else -> null
        }
    }
}