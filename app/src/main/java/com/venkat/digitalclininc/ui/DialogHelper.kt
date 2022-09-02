package com.venkat.digitalclininc.ui

import android.app.AlertDialog
import android.content.Context
import com.venkat.digitalclininc.R

// TODO: Under constructions
class DialogHelper {

    fun showOkDialog(
        context: Context,
        message: String,
        title: String? = null
    ) {
        createDialogBuilder(context, message, title)
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    fun showLogoutDialog(
        context: Context,
        message: String,
        title: String? = null,
        listener: () -> Unit
    ) {
        createDialogBuilder(context, message, title)
            .setPositiveButton(R.string.logout) { dialog, _ ->
                dialog.dismiss()
                listener()
            }
            .show()
    }

    fun showRetryDialog(
        context: Context,
        message: String,
        title: String? = null,
        listener: () -> Unit
    ) {
        createDialogBuilder(context, message, title)
            .setPositiveButton(R.string.retry) { dialog, _ ->
                dialog.dismiss()
                listener()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
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

//    companion object {
//        private var dialogHelper: DialogHelper? = null
//
//        fun getInstance(): DialogHelper {
//            if (dialogHelper == null) {
//                dialogHelper = DialogHelper()
//            }
//            return dialogHelper!!
//        }
//    }
}