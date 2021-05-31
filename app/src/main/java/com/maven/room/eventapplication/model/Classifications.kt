package com.maven.room.eventapplication.model

import com.google.gson.annotations.SerializedName

data class Classifications(

    @SerializedName("primary") var primary: Boolean,
    @SerializedName("segment") var segment: Segment,
    @SerializedName("genre") var genre: Genre,
    @SerializedName("subGenre") var subGenre: SubGenre

)