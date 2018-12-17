package com.garr.pavelbobrovko.data.repositories

import android.content.Context
import android.content.SharedPreferences
import com.garr.pavelbobrovko.data.db.dao.NoteDao
import com.garr.pavelbobrovko.data.db.entity.transformToDB
import com.garr.pavelbobrovko.data.db.entity.transformToDomain
import com.garr.pavelbobrovko.data.entity.NoteDeleteRequest
import com.garr.pavelbobrovko.data.entity.transformToNoteDb
import com.garr.pavelbobrovko.data.entity.transformToNoteRequest
import com.garr.pavelbobrovko.data.net.CloudService
import com.garr.pavelbobrovko.domain.entity.Note
import com.garr.pavelbobrovko.domain.repositories.NoteRepository
import com.garr.pavelbobrovko.domain.utils.PreferenceConst
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.rxkotlin.subscribeBy

class NoteRepositoryImpl(private val noteDao: NoteDao, private val apiService: CloudService
                         , mCtx: Context) : NoteRepository {
    private val sPref: SharedPreferences = mCtx.getSharedPreferences(PreferenceConst.DATA_PREFERENCES, Context.MODE_PRIVATE)

    private var email = sPref.getString(PreferenceConst.EMAIL_PREFERENCE,"")
    private var token = sPref.getString(PreferenceConst.TOKEN_PREFERENCE,"")

    override fun getList(): Flowable<ArrayList<Note>> {
        return noteDao.getAll()
                .take(1)
                .map { list ->
                    list.map { noteDb ->
                        noteDb.transformToDomain()
                    } as ArrayList<Note>
                }
    }

    override fun get(id: String): Flowable<Note> {
        return noteDao.getById(id)
                .take(1)
                .map {
                    it.transformToDomain()
                }
    }

    override fun update(note: Note): Completable {
       //return noteDao.update(note.transformToDB())
        return if (token.isNotEmpty()){
            if (note.netId.isNotEmpty()){
                noteDao.update(note.transformToDB())
                        .doOnComplete {
                            apiService.updateNote(email, note.transformToNoteRequest())
                        }
            }else{
                apiService.addNote(email,note.transformToNoteRequest())
                        .flatMapCompletable {
                            noteDao.update(it.transformToNoteDb())
                        }
            }

        }else{
            noteDao.update(note.transformToDB())
        }
    }

    override fun delete(note: Note): Completable {
        return noteDao.delete(note.transformToDB())
                .doOnComplete {
                   if (token.isNotEmpty() && note.netId.isNotEmpty()){
                       apiService.deleteNote(email, NoteDeleteRequest(note.netId))
                   }
                }
    }

    override fun set(note: Note): Completable {
        return if(token.isNotEmpty()){
            apiService.addNote(email,note.transformToNoteRequest())
                    .flatMapCompletable {
                        noteDao.insert(it.transformToNoteDb())
                    }
        } else noteDao.insert(note.transformToDB())
    }


}