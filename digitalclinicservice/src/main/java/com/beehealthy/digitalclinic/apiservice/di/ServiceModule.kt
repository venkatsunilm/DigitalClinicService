/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.beehealthy.digitalclinic.apiservice.di

import com.beehealthy.digitalclinic.apiservice.api.EventsService
import com.beehealthy.digitalclinic.apiservice.api.RepositoryServiceManager
import com.beehealthy.digitalclinic.apiservice.api.requests.IEventsReq
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@InstallIn(SingletonComponent::class)
//@Module
class ServiceModule {

//    @Singleton
//    @Provides
//    fun provideRepositoryService(eventsService: EventsService): RepositoryServiceManager {
//        return RepositoryServiceManager.getInstance(eventsService)
//    }

//    @Provides
//    fun provideEventService(): IEventsReq {
//        return EventsService.getInstance()
//    }
}
