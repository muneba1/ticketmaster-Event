package com.maven.room.eventapplication.model

import com.google.gson.annotations.SerializedName

data class Start(

    @SerializedName("localDate") var localDate: String,
    @SerializedName("dateTBD") var dateTBD: Boolean,
    @SerializedName("dateTBA") var dateTBA: Boolean,
    @SerializedName("dateTime") var dateTime: String,
    @SerializedName("timeTBA") var timeTBA: Boolean,
    @SerializedName("noSpecificTime") var noSpecificTime: Boolean

)