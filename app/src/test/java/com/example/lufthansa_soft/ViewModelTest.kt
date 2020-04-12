package com.example.lufthansa_soft

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.lufthansa_soft.model.*
import com.example.lufthansa_soft.network.ApiService
import com.example.lufthansa_soft.repository.Repository
import com.example.lufthansa_soft.utils.Constants
import com.example.lufthansa_soft.viewModel.AirportState
import com.example.lufthansa_soft.viewModel.AuthState
import com.example.lufthansa_soft.viewModel.FlightScheduleState
import com.example.lufthansa_soft.viewModel.SharedViewModel
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ViewModelTest {

    private val apiService = mock<ApiService>()
    private var repository = mock<Repository>()
    private var viewModel = mock<SharedViewModel>()
    private var observer = mock<Observer<AuthState>>()
    private var flightobserver = mock<Observer<FlightScheduleState>>()
    private var airportobserver = mock<Observer<AirportState>>()

    @get:Rule var testSchedulerRule = RxSchedulerTestRule()

    @get:Rule var rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        repository = Repository(apiService)
        viewModel = SharedViewModel(repository)
    }

    @Test
    fun verifyGetTokenIsCalled() {
        val tokenResponse = TokenResponse(
            accessToken = "8hsbau84wakaav65", tokenType = "bearer", expiresIn = 129600)

        whenever(apiService.retrieveToken(
            Constants.CLIENT_ID,
            Constants.CLIENT_SECRET,
            Constants.GRANT_TYPE)).thenReturn(Single.just(tokenResponse))

        viewModel.loading.observeForever(observer)

        viewModel.getToken( Constants.CLIENT_ID,
            Constants.CLIENT_SECRET,
            Constants.GRANT_TYPE)

        verify(observer).onChanged(AuthState.Success(tokenResponse.accessToken!!))
        verify(apiService, times(1)).retrieveToken(Constants.CLIENT_ID,
            Constants.CLIENT_SECRET,
            Constants.GRANT_TYPE)

    }

    @Test
    fun `verify GetError On Token Get`() {
        whenever(apiService.retrieveToken(
            Constants.CLIENT_ID,
            Constants.CLIENT_SECRET,
            Constants.GRANT_TYPE)).thenReturn(Single.error(Throwable("Api error")))

        viewModel.loading.observeForever(observer)

        viewModel.getToken( Constants.CLIENT_ID,
            Constants.CLIENT_SECRET,
            Constants.GRANT_TYPE)

        verify(observer).onChanged(AuthState.Error("Api error"))
        assertNotNull(viewModel.loading)
        assertEquals(AuthState.Error("Api error"), viewModel.loading.value)
    }

    @Test
    fun `verify not Succesful schedule call`() {

        val origin = "AAL"
        val destination = "BIL"
        val time = "2020-04-03"
        whenever(apiService.getAirlineSchedules(
            origin, destination, time)).thenReturn(Single.error(Throwable("Api error")))

        viewModel.flightScheduleData.observeForever(flightobserver)

        viewModel.getSchedules(origin, destination, time)

        verify(flightobserver).onChanged(FlightScheduleState.Error("Api error"))
    }

    @Test
    fun `verify not successfull airport call`() {

        whenever(apiService.getAirports()).thenReturn(Single.error(Throwable("Api error")))

        viewModel.data.observeForever(airportobserver)

        viewModel.getAirports()

        verify(airportobserver).onChanged(AirportState.Error("Api error"))
    }

    @Test
    fun `verify successfull airport call`() {
        val fakeResponse = listOf(
            AirportItem(countryCode = "AA",
                cityCode = "AA",
                utcOffset = "33",
                position = null,
                timeZoneId = "122",
                locationType = "atlanta",
                names = null,
                airportCode = "AAB")
        )
        val airport =  Airports(airport = fakeResponse)
        val airports = Aiports(airportResource = AirportResource(airports = airport))
        whenever(apiService.getAirports()).thenReturn(Single.just(airports))

        viewModel.data.observeForever(airportobserver)

        viewModel.getAirports()

        verify(apiService, times(1)).getAirports()
        verify(airportobserver).onChanged(AirportState.Success(fakeResponse))
    }
}