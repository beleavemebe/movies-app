package com.github.beleavemebe.moviesapp.model

import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("suggested_link_text")
    val suggestedLinkText: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)
