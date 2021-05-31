package com.maven.room.eventapplication.model

import com.google.gson.annotations.SerializedName

   
data class State (

   @SerializedName("name") var name : String,
   @SerializedName("stateCode") var stateCode : String

)