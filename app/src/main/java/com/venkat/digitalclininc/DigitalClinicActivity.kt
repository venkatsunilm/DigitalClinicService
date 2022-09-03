package com.venkat.digitalclininc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

// Single activity and navigated by fragment destinations using Navigation graph
// generates a hilt component for each Android class which is annotated with AndroidEntryPoint
@AndroidEntryPoint
class DigitalClinicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.digital_clinic_activity)
    }
}