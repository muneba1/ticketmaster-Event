package com.maven.room.eventapplication.model

import com.google.gson.annotations.SerializedName

   
data class Country (

   @SerializedName("name") var name : String,
   @SerializedName("countryCode") var countryCode : String

)