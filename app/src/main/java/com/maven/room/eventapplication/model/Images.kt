package com.maven.room.eventapplication.model

import com.google.gson.annotations.SerializedName
data class  Images (

   @SerializedName("ratio") var ratio : String,
   @SerializedName("url") var url : String,
   @SerializedName("width") var width : Int,
   @SerializedName("height") var height : Int,
   @SerializedName("fallback") var fallback : Boolean

)