package com.garr.pavelbobrovko.data.entity

import com.google.gson.annotations.SerializedName

data class NoteDeleteRequest(@SerializedName("objectId")
                             val id: String = "")