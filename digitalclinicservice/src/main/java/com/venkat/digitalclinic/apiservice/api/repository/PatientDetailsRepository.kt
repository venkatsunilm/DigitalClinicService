package com.venkat.digitalclinic.apiservice.api.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.venkat.digitalclinic.apiservice.api.contracts.IPatientDetailsRepository
import com.venkat.digitalclinic.apiservice.api.mockdata.EventsMockList
import com.venkat.digitalclinic.apiservice.helper.RetrofitClient
import com.venkat.digitalclinic.apiservice.models.DigitalClinic
import com.venkat.digitalclinic.apiservice.models.PatientEvent
import com.venkat.digitalclinic.apiservice.models.PatientPrescription
import com.venkat.digitalclinic.apiservice.models.ResponseObject
import com.venkat.digitalclinic.apiservice.utils.AppPreference
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PatientDetailsRepository @Inject constructor(@ApplicationContext val context: Context) :
    IPatientDetailsRepository {

    private var patientDetailsService: IPatientDetailsService = RetrofitClient.getInstance()
        .create(IPatientDetailsService::class.java)

    private var appPreference: AppPreference = AppPreference.getInstance(context)

    override suspend fun getEvents(): List<PatientEvent> {
        val token = appPreference.getString(AppPreference.Keys.TOKEN)
        return patientDetailsService.getEvents(token)
    }

    override fun getPrescriptions(): LiveData<ResponseObject<List<PatientPrescription>>> {
        val responseData: MutableLiveData<ResponseObject<List<PatientPrescription>>> =
            MutableLiveData()
        val token = appPreference.getString(AppPreference.Keys.TOKEN)
        patientDetailsService.getPrescriptions(token)
            .enqueue(object : Callback<List<PatientPrescription>> {

                override fun onFailure(call: Call<List<PatientPrescription>>, t: Throwable) {
                    responseData.postValue(
                        ResponseObject(
                            EventsMockList.getPrescriptionMockList(),
                            t.message
                        )
                    )
                }

                override fun onResponse(
                    call: Call<List<PatientPrescription>>,
                    response: Response<List<PatientPrescription>>
                ) {
                    if (response.isSuccessful) {
                        responseData.postValue(
                            ResponseObject(
                                response.body(),
                                statusCode = response.code()
                            )
                        )
                    } else {
                        responseData.postValue(
                            ResponseObject(
                                null,
                                response.message(),
                                response.code()
                            )
                        )
                    }
                }

            })
        return responseData
    }

    override fun getDigitalClinicInfo(): LiveData<ResponseObject<DigitalClinic>> {
        val responseData: MutableLiveData<ResponseObject<DigitalClinic>> = MutableLiveData()
        val token = appPreference.getString(AppPreference.Keys.TOKEN)
        patientDetailsService.getDigitalClinic(token)
            .enqueue(object : Callback<DigitalClinic> {

                override fun onFailure(call: Call<DigitalClinic>, t: Throwable) {
                    responseData.postValue(ResponseObject(null, t.message))
                }

                override fun onResponse(
                    call: Call<DigitalClinic>,
                    response: Response<DigitalClinic>
                ) {
                    if (response.isSuccessful) {
                        responseData.postValue(
                            ResponseObject(
                                response.body(),
                                statusCode = response.code()
                            )
                        )
                    } else {
                        responseData.postValue(
                            ResponseObject(
                                null,
                                response.message(),
                                response.code()
                            )
                        )
                    }
                }

            })
        return responseData
    }

    companion object {
        private var patientDetailsRepository: PatientDetailsRepository? = null
        const val DATA_KEY = "data"

        // TODO: remove this context and use the hilt context once available
        fun getInstance(context: Context): PatientDetailsRepository {
            if (patientDetailsRepository == null) {
                patientDetailsRepository = PatientDetailsRepository(context)
            }
            return patientDetailsRepository!!
        }
    }
}