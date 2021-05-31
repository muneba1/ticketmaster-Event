package com.maven.room.eventapplication.repositories

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.paging.PageKeyedDataSource
import com.maven.room.eventapplication.constant.EventConstants.*
import com.maven.room.eventapplication.database.Event
import com.maven.room.eventapplication.model.Events
import com.maven.room.eventapplication.network.ApiService
import ke.co.appslab.androidpagingwithcoroutines.networking.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class EventsDataSource(coroutineContext: CoroutineContext) : PageKeyedDataSource<String, Events>() {

    private val apiService = ApiClient.getClient().create(ApiService::class.java)
    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, Events>
    ) {
        scope.launch {
            try {
                val response = apiService.fetchEvents()
                when {
                    response.isSuccessful -> {
                        val listing = response.body()?.Embedded
                        val eventResponse = listing?.events?.map { it }
                        callback.onResult(eventResponse ?: listOf(), null, FIRST_PAGE.toString())
                    }
                }
            } catch (exception: Exception) {
                Log.e("Events", "Failed to fetch data! $ exception")
            }

        }

    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Events>) {
        scope.launch {
            try {
                val response =
                    apiService.fetchEvents(API_KEY, PAGE_SIZE, (FIRST_PAGE + 1).toString())
                when {
                    response.isSuccessful -> {
                        val listing = response.body()?.Embedded
                        val items = listing?.events?.map { it }
                        callback.onResult(
                            items ?: listOf(),
                            (FIRST_PAGE + 1).toString()
                        )
                    }
                }

            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }

    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Events>) {
        scope.launch {
            try {
                val response =
                    apiService.fetchEvents(API_KEY, PAGE_SIZE, params.key)
                when {
                    response.isSuccessful -> {
                        val listing = response.body()?.Embedded
                        val items = listing?.events?.map { it }
                        callback.onResult(items ?: listOf(), FIRST_PAGE.toString())
                    }
                }

            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }
    }
}