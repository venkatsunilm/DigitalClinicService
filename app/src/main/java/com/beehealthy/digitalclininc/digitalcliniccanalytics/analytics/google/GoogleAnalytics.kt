package com.gm.hmi.hvac.gmanalytics.analytics.google

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.FirebaseDatabase

/**
 * This class helps to call the required features of Google (Firebase) Analytics and to Log events.
 * @param context : Pass an Activity context
 */
class GoogleAnalytics(private val context: Context) {

    private var mFirebaseAnalytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)

    init {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }

    /**
     * Helps to log an event to Firebase     *
     * @param name -
     * @param params
     */
    fun logEvent(name: String, params: Bundle) {
        mFirebaseAnalytics.logEvent(name, params)
    }

    fun sendUserProperty(key: String, value: String){
        mFirebaseAnalytics.setUserProperty(key, value)
    }

    fun sendScreenName(context: Activity, name: String) {
        mFirebaseAnalytics.setCurrentScreen(context, name, null /* class override */)
    }
}