package com.garr.pavelbobrovko.data.entity

import com.google.gson.annotations.SerializedName

data class NoteResponse(
        @SerializedName("objectId")
        val id: String = "",
        val localId: String = "",
        val title: String = "",
        val subTitle: String = "",
        val note: String = "",
        val date: String = ""
)