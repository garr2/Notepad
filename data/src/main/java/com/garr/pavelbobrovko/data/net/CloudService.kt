package com.garr.pavelbobrovko.data.net

import com.garr.pavelbobrovko.data.entity.NoteDeleteRequest
import com.garr.pavelbobrovko.data.entity.NoteRequest
import com.garr.pavelbobrovko.data.entity.NoteResponse
import com.garr.pavelbobrovko.data.net.base.BaseRestService
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Observable

class CloudService(apiUrl: String): BaseRestService(apiUrl) {

    private lateinit var restApi: CloudApi

    override fun buildApiClass() {
       restApi = retrofit.create(CloudApi::class.java)
    }

    fun getNotes(tableName: String): Observable<ArrayList<NoteResponse>>{
        return restApi.getNotes(tableName)
    }

    fun getNoteById(tableName: String, id: String): Observable<NoteResponse>{
        return restApi.getNoteById(tableName,id)
    }

    fun updateNote(tableName: String, noteRequest: NoteRequest): Observable<NoteResponse>{
        return restApi.updateNote(tableName,noteRequest)
    }

    fun addNote(tableName: String, noteRequest: NoteRequest): Observable<NoteResponse>{
        return restApi.addNote(tableName, noteRequest)
    }

    fun deleteNote(tableName: String, noteDeleteRequest: NoteDeleteRequest){
        restApi.deleteNote(tableName,noteDeleteRequest)
    }
}