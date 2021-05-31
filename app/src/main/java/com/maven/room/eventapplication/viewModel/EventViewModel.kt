package com.maven.room.eventapplication.viewModel

import androidx.lifecycle.*
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.maven.room.eventapplication.constant.EventConstants.PAGE_SIZE
import com.maven.room.eventapplication.database.Event
import com.maven.room.eventapplication.database.EventDatabaseRepository
import com.maven.room.eventapplication.model.Events
import com.maven.room.eventapplication.repositories.EventsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventViewModel(private val repository: EventDatabaseRepository) : ViewModel() {
    var mEventLiveData: LiveData<PagedList<Events>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(Integer.parseInt(PAGE_SIZE))
            .setEnablePlaceholders(false)
            .build()
        mEventLiveData = initializedPagedListBuilder(config).build()
    }

    fun getEvents(): LiveData<PagedList<Events>> = mEventLiveData

    private fun initializedPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<String, Events> {

        val dataSourceFactory = object : DataSource.Factory<String, Events>() {
            override fun create(): DataSource<String, Events> {
                return EventsDataSource(Dispatchers.IO)
            }
        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }

    val allFavorite: LiveData<List<Event>> = repository.getFavoriteEvents.asLiveData()

    fun isEventFavorite(id: String) = viewModelScope.launch {
        repository.getEventById(id)
    }

    fun insert(event: Event) = viewModelScope.launch {
        repository.insert(event)
    }

    fun deleteUnSelected(id: String) = viewModelScope.launch {
        repository.deleteEvent(id)
    }
}

class EventViewModelFactory(private val repository: EventDatabaseRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EventViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}