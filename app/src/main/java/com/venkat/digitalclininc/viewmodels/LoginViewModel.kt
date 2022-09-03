package com.venkat.digitalclininc.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.venkat.digitalclinic.apiservice.api.RepositoryServiceManager
import com.venkat.digitalclinic.apiservice.helper.ResponseError
import com.venkat.digitalclinic.apiservice.utils.AppPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject internal constructor(
    @ApplicationContext private val context: Context,
    private val repositoryServiceManager: RepositoryServiceManager
) : ViewModel() {

    private val _username = MutableLiveData<String>("venkatsunilm@gmail.com")
    val userName: LiveData<String>
        get() = _username

    private val _password = MutableLiveData<String>("IDon'tKnow")
    val password: LiveData<String>
        get() = _password

    private val _spinner = MutableLiveData<Boolean>(false)
    val spinner: LiveData<Boolean>
        get() = _spinner

    fun login(): LiveData<String> {
        val responseObject = MutableLiveData<String>()
        viewModelScope.launch {
            _spinner.value = true
            try {
                responseObject.value = repositoryServiceManager.login(
                    _username.value!!,
                    _password.value!!
                )

                responseObject.value?.let { token ->
                    onUserLoggedIn(token)
                }
            } catch (error: ResponseError) {
                // TODO: Update the UI with the error message
                // TODO: For now  the service is not available sending mock data back
                responseObject.value = "token akjsdjsnjsnldjcnlsjncdjskjvbksjbvjdnfvkjdnf"
            } finally {
                _spinner.value = false
            }
        }
        return responseObject
    }

    private fun onUserLoggedIn(token: String) {
        // Token is stored here once the user is logged in.
        AppPreference.getInstance(context)
            .putString(AppPreference.Keys.TOKEN.name, token)
    }
}