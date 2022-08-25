package com.gm.hmi.hvac.gmanalytics.analytics.flurry

import android.content.Context
import androidx.annotation.NonNull
import com.flurry.android.FlurryAgent
import com.flurry.android.FlurryPerformance

/**
 * This class helps to call the required features of Flurry Analytics and to Log events.
 * @param context : Pass an Activity context
 */
class FlurryAnalytics(private val context: Context) {

    init {
        FlurryAgent.Builder()
                .withLogEnabled(true)
                .withPerformanceMetrics(FlurryPerformance.All)
                .build(context, "H8RG9754YFMKBWD6QJ5H")
    }

    /**
     *Call this event to start logging the events
     *
     * @param header the event name
     * @param values up to 10 params can be logged with each event
     * @param timeStampStartStatus Log the timed event when the user starts the event
     * setting the third param to true creates a timed event
     */
    fun logEvent(@NonNull header: String, @NonNull values: Map<String, String> = HashMap<String, String>(),
                 timeStampStartStatus: Boolean = false) {

        when {
            values.isNotEmpty() -> when {
                !timeStampStartStatus -> FlurryAgent.logEvent(header, values)
                else -> FlurryAgent.logEvent(header, values, timeStampStartStatus)
            }
            else -> when {
                !timeStampStartStatus -> FlurryAgent.logEvent(header)
                else -> FlurryAgent.logEvent(header, timeStampStartStatus)
            }
        }
    }

    /**
     *  Call this method to end the timed event, when the user navigates away from a particular
     *  event
     *  @param header : the event name which has started or passed earlier in the @logEvent method
     */
    fun endTimedEvent(@NonNull header: String) {
        FlurryAgent.endTimedEvent(header)
    }
}