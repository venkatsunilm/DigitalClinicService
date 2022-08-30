/*
 * Copyright (c) 2021 F-secure Corporation.
 */

package com.beehealthy.digitalclinic.apiservice.models

data class ResponseObject<T>(val data: T? = null, val apiException: String? = null, val statusCode: Int = 0)