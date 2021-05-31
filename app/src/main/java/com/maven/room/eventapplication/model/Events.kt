package com.maven.room.eventapplication.model

import com.google.gson.annotations.SerializedName

data class Events(

    @SerializedName("name") var name: String,
    @SerializedName("type") var type: String,
    @SerializedName("id") var id: String,
    @SerializedName("test") var test: Boolean,
    @SerializedName("url") var url: String,
    @SerializedName("locale") var locale: String,
    @SerializedName("images") var images: List<Images>,
    @SerializedName("sales") var sales: Sales,
    @SerializedName("dates") var dates: Dates,
    @SerializedName("classifications") var classifications: List<Classifications>,
    @SerializedName("promoter") var promoter: Promoter,
    @SerializedName("_links") var Links: Links,
    @SerializedName("_embedded") var Embedded: Embedded

) : java.io.Serializable

