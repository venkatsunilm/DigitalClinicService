package com.beehealthy.digitalclininc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.beehealthy.digitalclininc.digitalcliniccanalytics.ProjectAnalytics
import dagger.hilt.android.AndroidEntryPoint

// Single activity and navigated by fragment destinations using Navigation graph
// generates a hilt component for each Android class which is annotated with AndroidEntryPoint
@AndroidEntryPoint
class DigitalClinicActivity : AppCompatActivity() {

    val TAG = "venkat"
    override fun onCreate(savedInstanceState: Bundle?) {

        object {}.javaClass.enclosingMethod?.name?.let {
            ProjectAnalytics.getInstance(this)
                .sendEvent(object {}.javaClass.enclosingClass.simpleName, it)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.digital_clinic_activity)
    }
}