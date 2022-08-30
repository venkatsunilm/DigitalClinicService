/*
 * Copyright (c) 2021 F-secure Corporation.
 */

package com.beehealthy.digitalclinic.apiservice.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext

// TODO: Move this to other module as necessary
class AppPreference(@ApplicationContext private var context: Context) {
    companion object {
        private lateinit var masterKeyAlias: MasterKey
        private var appPreference: AppPreference? = null
        fun getInstance(context: Context): AppPreference {
            if (appPreference == null) {

                masterKeyAlias = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build()

                appPreference = AppPreference(context.applicationContext)
            }
            return appPreference!!
        }
    }

    private val sharedPreferencesName = "auth_digital_clinic"
    private val sharedPreferenceObject = EncryptedSharedPreferences.create(
        context,
        sharedPreferencesName,
        masterKeyAlias,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
    private var sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferenceObject.edit()

    enum class Keys {
        TOKEN,
        DATA_ID
    }

    fun putString(key: String, text: String) {
        with(sharedPreferencesEditor) {
            putString(key, text).apply()
//            commit()
        }
    }

    fun getString(str: Keys): String {
        return sharedPreferenceObject.getString(str.name, "")!!
    }

    fun putLong(key: String, value: Long) {
        with(sharedPreferencesEditor) {
            putLong(key, value).apply()
//            commit()
        }
    }

    fun getLong(key: String): Long {
        return sharedPreferenceObject.getLong(key, 0)

    }

    fun clearPreference() {
        with(sharedPreferencesEditor) { clear() }
        if (sharedPreferenceObject.contains(sharedPreferencesName)) {
            with(sharedPreferencesEditor) { remove(sharedPreferencesName).apply() }
        }
    }
}