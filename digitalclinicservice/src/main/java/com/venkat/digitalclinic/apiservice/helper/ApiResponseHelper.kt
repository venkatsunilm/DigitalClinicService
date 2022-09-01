/*
 * Copyright (c) 2021 F-secure Corporation.
 */

package com.venkat.digitalclinic.apiservice.helper

import android.content.Context
import com.venkat.digitalclinic.apiservice.models.ResponseObject
import com.venkat.digitalclinic.apiservice.utils.Constant.RETRY_STATUS_CODES

// TODO: As I am handling the retry in the service on failure and timeout
//  I am moving or will be removing this Temporary class
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
            } else if (responseObject.statusCode == 403) {
            } else if (RETRY_STATUS_CODES.contains(responseObject.statusCode)) {
            } else {
                // TODO: Enable this dialog when the back end API's are ready.
            }
        }
    }
}