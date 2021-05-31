package com.maven.room.eventapplication.model

import com.google.gson.annotations.SerializedName
import com.maven.room.eventapplication.model.*


data class Venues (

    @SerializedName("name") var name : String,
    @SerializedName("type") var type : String,
    @SerializedName("id") var id : String,
    @SerializedName("test") var test : Boolean,
    @SerializedName("locale") var locale : String,
    @SerializedName("postalCode") var postalCode : String,
    @SerializedName("timezone") var timezone : String,
    @SerializedName("city") var city : City,
    @SerializedName("state") var state : State,
    @SerializedName("country") var country : Country,
    @SerializedName("address") var address : Address,
    @SerializedName("location") var location : Location,
    @SerializedName("markets") var markets : List<Markets>,
    @SerializedName("_links") var Links : Links

)