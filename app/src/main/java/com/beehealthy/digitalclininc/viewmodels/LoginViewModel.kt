package com.beehealthy.digitalclininc.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beehealthy.digitalclinic.apiservice.api.RepositoryServiceManager
import com.beehealthy.digitalclinic.apiservice.models.ResponseObject
import com.beehealthy.digitalclinic.apiservice.utils.AppPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject internal constructor(
    private val repositoryServiceManager: RepositoryServiceManager
) : ViewModel() {

    val username: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply { value = "" }
    }
    val password: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply { value = "" }
    }

    val showProgressBar: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().apply { false }
    }

    val openMainActivity: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().apply { false }
    }

    fun login(): MutableLiveData<ResponseObject<String>> {
        showProgressBar.value = true
        return repositoryServiceManager.login(username.value!!, password.value!!)
    }

    fun onUserLoggedIn(token: String, context: Application) {
        showProgressBar.value = false
        // Token is stored here once the user is logged in.
        AppPreference.getInstance(context)
            .putString(AppPreference.Keys.TOKEN.name, token)
        openMainActivity.value = true
    }
}