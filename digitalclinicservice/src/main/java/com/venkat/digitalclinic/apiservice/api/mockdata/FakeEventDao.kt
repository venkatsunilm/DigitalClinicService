package com.venkat.digitalclinic.apiservice.api.mockdata

// TODO: implement room dependencies
// annotate this class as @Dao
// Pass this class as dependency to the RepositoryServiceManager
sealed interface FakeEventDao {
    fun getEvents()
}