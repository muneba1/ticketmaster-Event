package com.maven.room.eventapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Event::class], version = 1, exportSchema = false)
public abstract class EventDatabase : RoomDatabase() {

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
                    .addCallback(EventDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class EventDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

        }


        suspend fun populateDatabase(eventDao: EventDao) {
            var event = Event("dummyId", "dummyName", "dummyType", "dummyUrl", "dummyDate")
            eventDao.insert(event)
        }
    }
}