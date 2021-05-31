package com.maven.room.eventapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Event::class], version = 1, exportSchema = false)
abstract class EventDatabase : RoomDatabase() {

    abstract fun wordDao(): EventDao

    companion object {

        @Volatile
        private var INSTANCE: EventDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): EventDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EventDatabase::class.java,
                    "event_database"
                ).fallbackToDestructiveMigration()
                    .addCallback(EventDatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class EventDatabaseCallback : RoomDatabase.Callback()
    }
}