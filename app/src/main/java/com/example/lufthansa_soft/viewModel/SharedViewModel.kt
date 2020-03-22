package com.example.lufthansa_soft.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lufthansa_soft.Constants
import com.example.lufthansa_soft.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class SharedViewModel(val apiService: ApiService): ViewModel() {

    private val _loading = MutableLiveData<AuthState>()

    val loading : LiveData<AuthState>
        get() = _loading

    private lateinit var disposable: Disposable

    init {
        getToken(
            Constants.CLIENT_ID,
            Constants.CLIENT_SECRET,
            Constants.GRANT_TYPE
        )
    }


    fun getToken(client_id: String, client_secret: String, grant_type: String) {
        disposable = apiService.retrieveToken(
            client_id,
            client_secret,
            grant_type)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                if (it == null) {
                    _loading.postValue(AuthState.Loading)
                } else {
                    _loading.postValue(
                        AuthState.Success(
                            it.accessToken ?: ""
                        )
                    )
                }
            }, {
                _loading.postValue(
                    AuthState.Error(
                        it.message
                    )
                )
            })
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}

sealed class AuthState {

    object Loading: AuthState()
    data class Success(val token: String): AuthState()
    data class Error(val error: String?) : AuthState()
}