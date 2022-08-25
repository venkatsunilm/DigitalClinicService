package com.beehealthy.digitalclininc.digitalcliniccanalytics.analytics

/**
 * Helps to pass a parameter in the constructor and to maintain singleton features intact
 */
open class SingletonHolder<out T : Any, in A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator
    @Volatile
    private var instance: T? = null

    fun getInstance(arg1: A): T {
        val checkInstance = instance
        if (checkInstance != null) {
            return checkInstance
        }

        return synchronized(this) {
            val checkInstanceAgain = instance
            if (checkInstanceAgain != null) {
                checkInstanceAgain
            } else {
                val created = creator!!(arg1)
                instance = created
                creator = null
                created
            }
        }
    }
}