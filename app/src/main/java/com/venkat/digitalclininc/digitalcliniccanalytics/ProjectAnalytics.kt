package com.venkat.digitalclininc.digitalcliniccanalytics

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import com.venkat.digitalclininc.digitalcliniccanalytics.analytics.SingletonHolder
import com.venkat.digitalclininc.digitalcliniccanalytics.analytics.flurry.FlurryAnalytics
import com.venkat.digitalclininc.digitalcliniccanalytics.analytics.google.GoogleAnalytics
import com.venkat.digitalclininc.digitalcliniccanalytics.analytics.usagestats.UsageStatsAnalytics
import com.venkat.digitalclininc.digitalcliniccanalytics.dto.InfoDto
import com.venkat.digitalclininc.digitalcliniccanalytics.util.ExcelOperations
import com.venkat.digitalclininc.digitalcliniccanalytics.util.FileOperations
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Singleton class for Analytics
 * The idea of this class to capture user engagement and event behaviour analytics via Custom,
 * Google and Flurry.
 * Custom: Stores the data in an excel format and retrieve the same manually from the external
 * storage.
 */
class ProjectAnalytics private constructor(val context: Context) {

    //TODO: See if we can get the App name and context dynamically
    private var googleAnalytics: GoogleAnalytics
    private var flurryAnalytics: FlurryAnalytics
    private var usageStatsAnalytics: UsageStatsAnalytics
    private var eventInfoMap: MutableMap<String, InfoDto.EventInfo>
    private var screenInfoMap: MutableMap<String, InfoDto.ScreenInfo>
    private var eventIndex = 1
    private var screenIndex = 1

    init {

        // initiate custom event holder
        eventInfoMap = HashMap<String, InfoDto.EventInfo>()
        screenInfoMap = HashMap<String, InfoDto.ScreenInfo>()

        // initiate Firebase Analytics
        googleAnalytics = GoogleAnalytics(context)

        // initiate Flurry Analytics
        flurryAnalytics = FlurryAnalytics(context)

        // initiate Usage Stats Analytics
        usageStatsAnalytics = UsageStatsAnalytics(context)

    }

    companion object : SingletonHolder<ProjectAnalytics, Context>(::ProjectAnalytics)

    fun getGoogleInstance(): GoogleAnalytics {
        return googleAnalytics
    }

    fun getFlurryInstance(): FlurryAnalytics {
        return flurryAnalytics
    }

    /**
     * Log any custom event which has to be registered with Analytics.
     * @param screenName
     * @param eventName
     */
    fun sendEvent(screenName: String, eventName: String) {

        if (!screenInfoMap.containsKey(screenName)) {
            eventIndex = 1
            eventInfoMap = HashMap<String, InfoDto.EventInfo>()
        }

        // Store the events in a map
        when {
            eventInfoMap.containsKey(eventName) -> {
                val eventData = eventInfoMap[eventName]
                val eventInfo = InfoDto.EventInfo(eventData!!.id, eventData.name,
                        eventData.count + 1)
                eventInfoMap[eventName] = eventInfo
            }
            else -> {
                val eventInfo = InfoDto.EventInfo("E_" + eventIndex++, eventName, 1)
                eventInfoMap[eventName] = eventInfo
            }
        }

        // TODO: Call this if there is any global navigation call on Screen changed
        // Update the events list based on the screen validation
        when {
            screenInfoMap.containsKey(screenName) -> {
                val screenData = screenInfoMap[screenName]
                val screenInfo = InfoDto.ScreenInfo(screenData!!.id, screenData.name,
                        screenData.count + 1, eventInfoMap)
                screenInfoMap[screenName] = screenInfo
            }
            else -> {
                val screenInfo = InfoDto.ScreenInfo("S_" + screenIndex++,
                        screenName, 1, eventInfoMap)
                screenInfoMap[screenName] = screenInfo
            }
        }

        // TODO: Based on the requirement to add event meta data info.
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, screenName)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, eventName)
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "event")
        googleAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)


        // TODO: Based on the requirement to add event meta data info.
        flurryAnalytics.logEvent(screenName)
        val articleParams = HashMap<String, String>()
        articleParams[eventName] = eventName
        articleParams[eventName + "1"] = eventName + "1"
        flurryAnalytics.logEvent(screenName, articleParams)

    }

    /**
     * Helps to store the collected data to an excel workbook
     * @param sheetName
     */
    private fun writeToWorkbook(sheetName: String) {
        ExcelOperations(screenInfoMap, sheetName)
    }

    /**
     * Helps to store the collected data to an text file
     */
    private fun writeToJsonFile() {
        FileOperations(screenInfoMap)
    }

    fun commit() {
        ExcelOperations(screenInfoMap, getAppLable(context))
        FileOperations(screenInfoMap)
    }

    private fun getAppLable(context: Context): String {
        val packageManager = context.packageManager
        var applicationInfo: ApplicationInfo? = null
        try {
            applicationInfo = packageManager.getApplicationInfo(context.applicationInfo.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
        }

        return (if (applicationInfo != null) packageManager.getApplicationLabel(applicationInfo) else "Unknown") as String
    }
}