package com.maven.room.eventapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_table")
data class Event(@PrimaryKey @ColumnInfo(name = "id") var id: String, val name: String, val type: String, val imageUrl: String, val startDate: String?)
