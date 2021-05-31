package com.maven.room.eventapplication.model

import com.google.gson.annotations.SerializedName

   
data class Attractions (

   @SerializedName("name") var name : String,
   @SerializedName("type") var type : String,
   @SerializedName("id") var id : String,
   @SerializedName("test") var test : Boolean,
   @SerializedName("locale") var locale : String,
   @SerializedName("images") var images : List<Images>,
   @SerializedName("classifications") var classifications : List<Classifications>,
   @SerializedName("_links") var Links : Links

)