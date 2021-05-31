package com.maven.room.eventapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Query("SELECT * FROM event_table ")
    fun getAllFavoriteEvents(): Flow<List<Event>>

    @Query("SELECT * FROM event_table where id=:id")
    fun getEventById(id: String): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(event: Event)

    @Query("DELETE FROM event_table")
    suspend fun deleteAll()

    @Query("DELETE FROM event_table where id=:id")
    suspend fun deleteSingleRecord(id: String)
}
