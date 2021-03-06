package com.example.lufthansa_soft.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lufthansa_soft.repository.Repository
import com.example.lufthansa_soft.model.AirportItem
import com.example.lufthansa_soft.model.testing.FlightItem
import com.example.lufthansa_soft.model.testing.Schedule
import com.example.lufthansa_soft.utils.addToCompositeDisposable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


class SharedViewModel(val repository: Repository): ViewModel() {

    private val _loading = MutableLiveData<AuthState>()
    private val _data = MutableLiveData<AirportState>()
    private val _flightScheduleData = MutableLiveData<FlightScheduleState>()

    val loading : LiveData<AuthState>
        get() = _loading

    val data : LiveData<AirportState>
        get() = _data

    val flightScheduleData: LiveData<FlightScheduleState>
        get() = _flightScheduleData

    private val compositeDisposable = CompositeDisposable()


    fun getToken(client_id: String, client_secret: String, grant_type: String) {
        repository.getToken(client_id,
            client_secret,
            grant_type)
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
            }).addToCompositeDisposable(compositeDisposable)
    }

    fun getAirports() {
        repository.getAirports()
            .subscribe({
                _data.postValue(
                    AirportState.Success(it.airportResource?.airports?.airport!! ))
            }, {
                _data.postValue(
                    AirportState.Error(it.message)
                )
            }).addToCompositeDisposable(compositeDisposable)
    }

    fun getSchedules(origin: String, destination: String, time: String) {
        repository.getSchedules(
            origin, destination, time)
            .subscribe({
                _flightScheduleData.postValue(
                    FlightScheduleState.Success(it.scheduleResource?.schedule?.flight!!))
            }, {
                _flightScheduleData.postValue(
                    FlightScheduleState.Error(it.message))
            }).addToCompositeDisposable(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
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