package com.beehealthy.digitalclinic.apiservice.api.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beehealthy.digitalclinic.apiservice.api.contracts.requests.IPatientDetailsRepository
import com.beehealthy.digitalclinic.apiservice.api.contracts.requests.IPatientDetailsService
import com.beehealthy.digitalclinic.apiservice.helper.RetrofitClient
import com.beehealthy.digitalclinic.apiservice.models.DigitalClinic
import com.beehealthy.digitalclinic.apiservice.models.PatientEvent
import com.beehealthy.digitalclinic.apiservice.models.PatientPrescription
import com.beehealthy.digitalclinic.apiservice.models.ResponseObject
import com.beehealthy.digitalclinic.apiservice.utils.AppPreference
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

    override fun getEvents(): LiveData<ResponseObject<List<PatientEvent>>> {
        val responseData: MutableLiveData<ResponseObject<List<PatientEvent>>> = MutableLiveData()
        val token = appPreference.getString(AppPreference.Keys.TOKEN)
        patientDetailsService.getEvents(token)
            .enqueue(object : Callback<List<PatientEvent>> {

                override fun onFailure(call: Call<List<PatientEvent>>, t: Throwable) {
                    responseData.postValue(ResponseObject(null, t.message))
                }

                override fun onResponse(
                    call: Call<List<PatientEvent>>,
                    response: Response<List<PatientEvent>>
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

    override fun getPrescriptions(): LiveData<ResponseObject<List<PatientPrescription>>> {
        val responseData: MutableLiveData<ResponseObject<List<PatientPrescription>>> =
            MutableLiveData()
        val token = appPreference.getString(AppPreference.Keys.TOKEN)
        patientDetailsService.getPrescriptions(token)
            .enqueue(object : Callback<List<PatientPrescription>> {

                override fun onFailure(call: Call<List<PatientPrescription>>, t: Throwable) {
                    responseData.postValue(ResponseObject(null, t.message))
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