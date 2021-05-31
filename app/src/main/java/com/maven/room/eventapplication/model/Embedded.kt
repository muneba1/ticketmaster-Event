package com.maven.room.eventapplication.model

import com.google.gson.annotations.SerializedName

data class Embedded (

   @SerializedName("events") var events : List<Events>

)