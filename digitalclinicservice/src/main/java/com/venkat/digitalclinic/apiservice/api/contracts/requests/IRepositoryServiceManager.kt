package com.venkat.digitalclinic.apiservice.api.contracts.requests

import com.venkat.digitalclinic.apiservice.api.contracts.IUserRepository

interface IRepositoryServiceManager : IUserRepository, IPatientDetailsRepository {
    fun initialize()
}