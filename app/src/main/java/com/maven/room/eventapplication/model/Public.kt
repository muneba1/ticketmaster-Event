package com.maven.room.eventapplication.model

import com.google.gson.annotations.SerializedName

data class Public(

    @SerializedName("startDateTime") var startDateTime: String,
    @SerializedName("startTBD") var startTBD: Boolean,
    @SerializedName("endDateTime") var endDateTime: String

)