/*
 * Copyright (c) 2021 F-secure Corporation.
 */

package com.venkat.digitalclinic.apiservice.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

// TODO: UNDER CONSTRUCTION
class AppPreference() {
    companion object {
        private lateinit var masterKeyAlias: MasterKey
        private var appPreference: AppPreference? = null
        private lateinit var applicationContext: Context
        fun getInstance(context: Context): AppPreference {
            if (appPreference == null) {
                applicationContext = context
                masterKeyAlias = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build()
                appPreference = AppPreference()
            }
            return appPreference!!
        }
    }

    private val sharedPreferencesName = "auth_digital_clinic"
    private val sharedPreferenceObject = EncryptedSharedPreferences.create(
        applicationContext,
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
        }
    }

    fun getString(str: Keys): String {
        return sharedPreferenceObject.getString(str.name, "")!!
    }

    fun putLong(key: String, value: Long) {
        with(sharedPreferencesEditor) {
            putLong(key, value).apply()
        }
    }

    fun getLong(key: String): Long {
        return sharedPreferenceObject.getLong(key, 0)

    }

    fun clearPreference() {
        with(sharedPreferencesEditor) { clear() }
        if (sharedPreferenceObject.contains(sharedPreferencesName)) {
            with(sharedPreferencesEditor) { remove(sharedPreferencesName).commit() }
        }
    }
}