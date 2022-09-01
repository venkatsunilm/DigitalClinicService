/*
 * Copyright (c) 2021 F-secure Corporation.
 */

package com.venkat.digitalclinic.apiservice.utils

import java.net.HttpURLConnection

object Constant {
    val RETRY_STATUS_CODES = arrayOf(HttpURLConnection.HTTP_UNAVAILABLE, HttpURLConnection.HTTP_GATEWAY_TIMEOUT)
}