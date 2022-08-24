package com.beehealthy.digitalclininc

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Now Hilt is attached to the Application object's lifecycle and provides dependencies to it
// Now the application level component is available
@HiltAndroidApp
class DigitalClinicApplication : Application() {
}