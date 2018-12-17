package com.garr.pavelbobrovko.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Notes")
data class NoteDb(

        @PrimaryKey(autoGenerate = true)
        val id: String? = null,

        val netId: String = "",
        val title: String,
        val subTitle: String,
        val note: String,
        val date: String
)