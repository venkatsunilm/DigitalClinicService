/*
 * Copyright (c) 2021 F-secure Corporation.
 */

package com.venkat.digitalclinic.apiservice.models

// builder pattern
data class ResponseObject<T>(val data: T? = null, val apiException: String? = null, val statusCode: Int = 0)