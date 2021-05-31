package com.maven.room.eventapplication.network

import com.maven.room.eventapplication.constant.EventConstants.API_KEY
import com.maven.room.eventapplication.constant.EventConstants.PAGE_SIZE
import com.maven.room.eventapplication.model.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("events.json?countryCode=US")
    suspend fun fetchEvents(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("size") loadSize: String = PAGE_SIZE,
        @Query("page") pageNumber: String = "1",
    ): Response<ResponseBody>
}