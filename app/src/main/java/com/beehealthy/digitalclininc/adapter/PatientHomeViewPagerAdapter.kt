package com.beehealthy.digitalclininc.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.beehealthy.digitalclininc.ui.EventListFragment
import com.beehealthy.digitalclininc.ui.PrescriptionListFragment
import com.beehealthy.digitalclininc.ui.VaccinationsFragment

const val EVENTS_PAGE = 0
const val PRESCRIPTION_PAGE = 1
const val VACCINATIONS_PAGE = 2

class PatientHomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreator: Map<Int, () -> Fragment> = mapOf(
        EVENTS_PAGE to { EventListFragment() },
        PRESCRIPTION_PAGE to { PrescriptionListFragment() },
        VACCINATIONS_PAGE to { VaccinationsFragment() }
    )

    override fun getItemCount(): Int = tabFragmentsCreator.size

    override fun createFragment(position: Int): Fragment {
        // return the Fragment object invoked as function
        // on success return the invoked Fragment or on failure throw exception
       return tabFragmentsCreator[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}