/*
 * Copyright (c) 2021 F-secure Corporation.
 */

package com.beehealthy.digitalclininc.helper

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.beehealthy.digitalclinic.apiservice.models.ResponseObject
import com.beehealthy.digitalclininc.R
import com.beehealthy.digitalclininc.ui.LoginFragment
import com.beehealthy.digitalclininc.util.Constant.RETRY_STATUS_CODES

class ApiResponseHelper {
    companion object {
        fun <T> handleApiResponse(
            context: Context,
            responseObject: ResponseObject<T>,
            listener: (T) -> Unit
        ) {
            if (responseObject.data != null) {
                listener(responseObject.data!!)
            } else if (responseObject.statusCode == 401) {
                showOkDialog(context, responseObject.apiException!!, "Authentication failed")
            } else if (responseObject.statusCode == 403) {
                showOkDialog(context, responseObject.apiException!!, "Unauthorized")
            } else if (RETRY_STATUS_CODES.contains(responseObject.statusCode)) {
                showRetryDialog(
                    context,
                    "Server is temporary unavailable, please try again after few minutes",
                    "Server is unavailable"
                )
            } else {
                // TODO: Enable this dialog when the back end API's are ready.
//                showLogoutDialog(context, "Server error")
            }
        }


        private fun showOkDialog(context: Context, message: String, title: String? = null) {
            createDialogBuilder(context, message, title)
                .setPositiveButton(android.R.string.ok) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        private fun showLogoutDialog(context: Context, message: String, title: String? = null) {
            createDialogBuilder(context, message, title)
                .setPositiveButton(R.string.logout) { dialog, _ ->
                    // TODO: Use Jetpack EncryptedSharedPreference to store token
                    // TODO:Delete storage and token shared preferences if any in future
                }
                .show()
        }

        private fun showRetryDialog(context: Context, message: String, title: String? = null) {
            createDialogBuilder(context, message, title)
                .setPositiveButton(R.string.retry) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        private fun createDialogBuilder(
            context: Context,
            message: String,
            title: String? = null
        ): AlertDialog.Builder {

            return AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
        }

    }
}