package com.maven.room.eventapplication.root

import android.app.Application
import com.maven.room.eventapplication.database.EventDatabase
import com.maven.room.eventapplication.database.EventDatabaseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class EventApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { EventDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { EventDatabaseRepository(database.wordDao()) }
}