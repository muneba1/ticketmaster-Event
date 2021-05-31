package com.maven.room.eventapplication.model

import com.google.gson.annotations.SerializedName

data class Dates (

   @SerializedName("start") var start : Start,
   @SerializedName("timezone") var timezone : String,
   @SerializedName("status") var status : Status

)