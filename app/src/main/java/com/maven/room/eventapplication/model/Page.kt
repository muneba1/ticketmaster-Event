package com.maven.room.eventapplication.model
import com.google.gson.annotations.SerializedName

data class Page (

   @SerializedName("size") var size : Int,
   @SerializedName("totalElements") var totalElements : Int,
   @SerializedName("totalPages") var totalPages : Int,
   @SerializedName("number") var number : Int

)