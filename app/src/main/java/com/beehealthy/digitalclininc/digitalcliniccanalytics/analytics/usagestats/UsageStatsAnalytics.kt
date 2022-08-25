package com.gm.hmi.hvac.gmanalytics.analytics.usagestats

import android.app.AppOpsManager
import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.os.Process
import android.provider.Settings
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class UsageStatsAnalytics(context: Context) {
    private var mUsageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
    private val mDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
    private val cal = Calendar.getInstance()
    private var interval = 0

    init {

        // Get the app statistics since one year ago from the current time.
        cal.add(Calendar.YEAR, -1)
        interval = UsageStatsManager.INTERVAL_YEARLY

        // TODO: This check might not be necessary for HMI device.
        if (true/*checkForUsageStatsPermission(context)*/) {
            getQueryEventsStats()
            getQueryEvents()
            getQueryEventsForSelf()
        } else { // Take the user to usage access setting directly to enable permissions
            context.startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        }

    }

    /**
     * To check usage stats permission dynamically
     */
    private fun checkForUsageStatsPermission(context: Context): Boolean {
        val appOps = context.getSystemService(Context.APP_OPS_SERVICE)!! as AppOpsManager
        val mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, Process.myUid(), context.getPackageName())
        return mode == AppOpsManager.MODE_ALLOWED
    }

    fun retrieveQueryUsageStats() {
        val stats = mUsageStatsManager.queryUsageStats(
                UsageStatsManager.INTERVAL_YEARLY,
                cal.timeInMillis,
                System.currentTimeMillis())

        for (stat in stats) {
            Log.d("Stats", " Stats ***  \npackageName: ${stat.packageName} , " +
                    "\nfirstTimeStamp: ${mDateFormat.format(Date(stat.firstTimeStamp))}, " +
                    "\nlastTimeStamp: ${mDateFormat.format(Date(stat.lastTimeStamp))} , " +
                    "\nlastTimeUsed: ${mDateFormat.format(Date(stat.lastTimeUsed))}, " +
//                    "\nlastTimeForegroundServiceUsed: ${mDateFormat.format(Date(stat.lastTimeForegroundServiceUsed))}, " +
//                    "\ntotalTimeForegroundServiceUsed: ${TimeUnit.MILLISECONDS.toSeconds(stat.totalTimeForegroundServiceUsed)}, " +
//                    "\nlastTimeVisible: ${mDateFormat.format(Date(stat.lastTimeVisible))}, " +
                    "\ntotalTimeInForeground: ${TimeUnit.MILLISECONDS.toMinutes(stat.totalTimeInForeground)}")
        }
    }

    fun retrieveQueryAndAggregateUsageStats() {
        val stats = mUsageStatsManager.queryAndAggregateUsageStats(
                cal.timeInMillis,
                System.currentTimeMillis())

        for ((packaNameKey, usageStatsValue) in stats) {
            Log.d("Aggregate Stats", " Stats *** ${packaNameKey} , " +
                    "firstTimeStamp: ${mDateFormat.format(Date(usageStatsValue.firstTimeStamp))}, " +
                    "lastTimeStamp: ${mDateFormat.format(Date(usageStatsValue.lastTimeStamp))} , " +
                    "lastTimeUsed: ${mDateFormat.format(Date(usageStatsValue.lastTimeUsed))}, " +
                    "totalTimeInForeground: ${TimeUnit.MILLISECONDS.toSeconds(usageStatsValue.totalTimeInForeground)}")
        }

    }

    fun getTopApp() {
        val time = System.currentTimeMillis()
        val stats = mUsageStatsManager.queryUsageStats(
                UsageStatsManager.INTERVAL_DAILY,
                time - 5000,
                System.currentTimeMillis())

        for (stat in stats) {
            Log.d("Top App Stats", " Stats *** ${stat.packageName} , " +
                    "firstTimeStamp: ${mDateFormat.format(Date(stat.firstTimeStamp))}, " +
                    "lastTimeStamp: ${mDateFormat.format(Date(stat.lastTimeStamp))} , " +
                    "lastTimeUsed: ${mDateFormat.format(Date(stat.lastTimeUsed))}, " +
                    "totalTimeInForeground: ${TimeUnit.MILLISECONDS.toSeconds(stat.totalTimeInForeground)}")
        }
    }

    fun getQueryEventsStats() {
        val stats = mUsageStatsManager.queryEventStats(
                UsageStatsManager.INTERVAL_DAILY,
                cal.timeInMillis,
                System.currentTimeMillis())

        for (stat in stats) {
            Log.d("getQueryEvents", " queryEvents Stats \ncount: ${stat.count} , " +
                    "\nfirstTimeStamp: ${mDateFormat.format(Date(stat.firstTimeStamp))}, " +
                    "\nlastTimeStamp: ${mDateFormat.format(Date(stat.lastTimeStamp))} , " +
                    "\neventType: ${stat.eventType}, " +
                    "\nlastEventTime: ${mDateFormat.format(Date(stat.lastEventTime))}, " +
                    "\ntotalTime: ${TimeUnit.MILLISECONDS.toSeconds(stat.totalTime)}")
        }
    }

    fun getQueryEvents() {
        val usageEvents = mUsageStatsManager.queryEvents(
                cal.timeInMillis,
                System.currentTimeMillis())

        while (usageEvents.hasNextEvent()) {
            val event = UsageEvents.Event()
            usageEvents.getNextEvent(event)
            Log.d("getQueryEvents", " queryEvents \npackageName: ${event.packageName} , " +
                    "\nclassName: ${event.className}, " +
                    "\neventType: ${event.eventType} , " +
                    "\ntimeStamp: ${mDateFormat.format(event.timeStamp)}, " +
                    "\nshortcutId: ${event.shortcutId}, " +
                    "\nconfiguration: ${event.configuration}")
        }
    }

    fun getQueryEventsForSelf() {
        val usageEvents = mUsageStatsManager.queryEventsForSelf(
                cal.timeInMillis,
                System.currentTimeMillis())

        while (usageEvents.hasNextEvent()) {
            val event = UsageEvents.Event()
            usageEvents.getNextEvent(event)
            Log.d("getQueryEvents", " queryEvents Self \npackageName: ${event.packageName} , " +
                    "\nclassName: ${event.className}, " +
                    "\neventType: ${event.eventType} , " +
                    "\ntimeStamp: ${mDateFormat.format(event.timeStamp)}, " +
                    "\nshortcutId: ${event.shortcutId}, " +
                    "\nconfiguration: ${event.configuration}")
        }
    }

}