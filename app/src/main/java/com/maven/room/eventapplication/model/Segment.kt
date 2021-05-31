package com.maven.room.eventapplication.model

import com.google.gson.annotations.SerializedName

data class Segment (

   @SerializedName("id") var id : String,
   @SerializedName("name") var name : String

)