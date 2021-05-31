package com.maven.room.eventapplication.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class EventDatabaseRepository(private val eventDao: EventDao) {

    val getFavoriteEvents: Flow<List<Event>> = eventDao.getAllFavoriteEvents()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getEventById(id: String): Boolean {
        return eventDao.getEventById("")
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(event: Event) {
        eventDao.insert(event)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteEvent(id: String) {
        eventDao.deleteSingleRecord(id)
    }
}