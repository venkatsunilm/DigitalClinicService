package com.venkat.digitalclininc.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.venkat.digitalclinic.apiservice.api.RepositoryServiceManager
import com.venkat.digitalclinic.apiservice.models.ResponseObject
import com.venkat.digitalclinic.apiservice.utils.AppPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject internal constructor(
    private val repositoryServiceManager: RepositoryServiceManager
) : ViewModel() {

    val username: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }
    val password: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }

    val showProgressBar: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val showEventListFragment: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun login(): MutableLiveData<ResponseObject<String>> {
        showProgressBar.value = true
        return repositoryServiceManager.login(username.value!!, password.value!!)
    }

    fun onUserLoggedIn(token: String, context: Context) {
        showProgressBar.value = false
        // Token is stored here once the user is logged in.
        AppPreference.getInstance(context)
            .putString(AppPreference.Keys.TOKEN.name, token)
        showEventListFragment.value = true
    }
}