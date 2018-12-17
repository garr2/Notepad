package com.garr.pavelbobrovko.domain.entity

data class Note(val id: String, val netId: String = "", val title: String, val subTitle: String = ""
                , val note: String = "", val date: String = "")
