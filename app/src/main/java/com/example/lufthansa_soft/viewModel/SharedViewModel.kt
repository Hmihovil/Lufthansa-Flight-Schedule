package com.example.lufthansa_soft.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lufthansa_soft.model.AirportItem
import com.example.lufthansa_soft.model.testing.FlightItem
import com.example.lufthansa_soft.network.ApiService
import com.google.android.gms.dynamic.IFragmentWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class SharedViewModel(val apiServicewithoutAuth: ApiService,
                      val apiServiceWithAuth: ApiService): ViewModel() {

    private val _loading = MutableLiveData<AuthState>()
    private val _data = MutableLiveData<AirportState>()
    private val _flightScheduleData = MutableLiveData<FlightScheduleState>()

    val loading : LiveData<AuthState>
        get() = _loading

    val data : LiveData<AirportState>
        get() = _data

    val flightScheduleData: LiveData<FlightScheduleState>
        get() = _flightScheduleData

    private lateinit var disposable: Disposable


    fun getToken(client_id: String, client_secret: String, grant_type: String) {
        disposable = apiServicewithoutAuth.retrieveToken(
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

    fun getAirports() {
        disposable = apiServiceWithAuth
            .getAirports()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _data.postValue(AirportState.Success(it.airportResource?.airports?.airport!! ))
            }, {
                _loading.postValue(
                    AuthState.Error(
                        it.message
                    )
                )
            })
    }

    fun getSchedules(origin: String, destination: String, time: String) {
        disposable = apiServiceWithAuth
            .getAirlineSchedules(origin, destination, time)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                if (it.scheduleResource == null) {
                    Log.e(">>>", "ffff")
                }
                _flightScheduleData.postValue(
                    FlightScheduleState.Success(it.scheduleResource?.schedule?.flight!!))
            }, {
                _flightScheduleData.postValue(
                    FlightScheduleState.Error(it.message))
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

sealed class AirportState {
    data class Success(val airportList: List<AirportItem>) : AirportState()
    data class Error(val error: String?) : AirportState()
}

sealed class FlightScheduleState {
    data class Success(val schedules: List<FlightItem>) : FlightScheduleState()
    data class Error(val error: String?) : FlightScheduleState()
}