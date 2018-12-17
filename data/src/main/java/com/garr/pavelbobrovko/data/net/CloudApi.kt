package com.garr.pavelbobrovko.data.net

import com.garr.pavelbobrovko.data.entity.NoteDeleteRequest
import com.garr.pavelbobrovko.data.entity.NoteRequest
import com.garr.pavelbobrovko.data.entity.NoteResponse
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*

interface CloudApi {

    @GET("data/{tableName}")
    fun getNotes(@Path("tableName")tableName: String): Observable<ArrayList<NoteResponse>>

    @GET("data/{tableName}/{id}")
    fun getNoteById(@Path("tableName")tableName: String,
                    @Path("id")netId: String): Observable<NoteResponse>

    @PUT("data/{tableName}")
    fun updateNote(@Path("tableName")tableName: String,
                   @Body noteRequest: NoteRequest): Observable<NoteResponse>

    @POST("data/{tableName}")
    fun addNote(@Path("tableName")tableName: String,
                @Body noteRequest: NoteRequest): Observable<NoteResponse>

    @DELETE("data/{tableName}")
    fun deleteNote(@Path("tableName")tableName: String,
                   @Body noteDeleteRequest: NoteDeleteRequest)
}