package com.garr.pavelbobrovko.data.db.dao

import android.arch.persistence.room.*
import com.garr.pavelbobrovko.data.db.entity.NoteDb
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface NoteDao {

    @Insert
    fun insertAll(noteList: ArrayList<NoteDb>)

    @Insert
    fun insert(note: NoteDb): Completable

    @Update
    fun update(note: NoteDb): Completable

    @Delete
    fun delete(note: NoteDb): Completable

    @Query("DELETE FROM Notes")
    fun deleteAll(): Completable

    @Query("SELECT * FROM Notes")
    fun getAll(): Flowable<ArrayList<NoteDb>>

    @Query("SELECT * FROM Notes WHERE id = :id LIMIT 1")
    fun getById(id: String): Flowable<NoteDb>
}