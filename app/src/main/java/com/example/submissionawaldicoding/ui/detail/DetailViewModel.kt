package com.example.submissionawaldicoding.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submissionawaldicoding.data.response.EventResponse
import com.example.submissionawaldicoding.data.response.ListEventsItem
import com.example.submissionawaldicoding.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailEventViewModel : ViewModel() {
    private val _eventDetail = MutableLiveData<ListEventsItem>()
    val eventDetail: MutableLiveData<ListEventsItem> get() = _eventDetail

    fun fetchEventDetail(id: String) {
        val apiService = ApiConfig.getApiService()
        val call = apiService.getDetailEvent(id)
        call.enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                if (response.isSuccessful) {
                    val eventDetailResponse = response.body()
                    if (eventDetailResponse != null && eventDetailResponse.listEvents.isNotEmpty()) {
                        _eventDetail.value = eventDetailResponse.listEvents[0]
                    }
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
            }
        })
    }
}