package com.garr.pavelbobrovko.domain.repositories

import com.garr.pavelbobrovko.domain.entity.Note
import io.reactivex.Completable
import io.reactivex.Flowable

interface NoteRepository: BaseRepository {

    fun getList(): Flowable<ArrayList<Note>>

    fun get(id: String): Flowable<Note>

    fun update(note: Note): Completable

    fun delete(note: Note): Completable

    fun set(note: Note): Completable
}