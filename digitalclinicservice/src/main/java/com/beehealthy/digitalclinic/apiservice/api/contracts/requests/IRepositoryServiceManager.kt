package com.beehealthy.digitalclinic.apiservice.api.contracts.requests

import com.beehealthy.digitalclinic.apiservice.api.contracts.IUserRepository

interface IRepositoryServiceManager : IUserRepository, IPatientDetailsRepository {
    fun initialize()
}