package com.venkat.digitalclininc

import android.app.Application
import com.venkat.digitalclininc.digitalcliniccanalytics.ProjectAnalytics
import dagger.hilt.android.HiltAndroidApp

// Now Hilt is attached to the Application object's lifecycle and provides dependencies to it
// Now the application level component is available
@HiltAndroidApp
class DigitalClinicApplication : Application() {
    override fun onCreate() {
        object {}.javaClass.enclosingMethod?.name?.let {
            ProjectAnalytics.getInstance(this)
                .sendEvent(object {}.javaClass.enclosingClass.simpleName, it)
        }

//        ApplicationConstants.applicationContext = this
        super.onCreate()
    }
}