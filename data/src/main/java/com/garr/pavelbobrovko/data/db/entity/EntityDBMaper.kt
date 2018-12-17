package com.garr.pavelbobrovko.data.db.entity

import com.garr.pavelbobrovko.domain.entity.Note

fun NoteDb.transformToDomain(): Note{
    return Note(id = this.id!!, title = this.title, subTitle = this.subTitle
            , note = this.note, date = this.date)
}

fun Note.transformToDB(): NoteDb{
    return NoteDb(id = id, title = title, subTitle = subTitle, note = note, date = date)
}