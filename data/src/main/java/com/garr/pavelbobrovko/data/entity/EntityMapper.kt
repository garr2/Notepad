package com.garr.pavelbobrovko.data.entity

import com.garr.pavelbobrovko.data.db.entity.NoteDb
import com.garr.pavelbobrovko.domain.entity.Note
import com.garr.pavelbobrovko.domain.entity.SignupData
import io.reactivex.Completable
import io.reactivex.Observable
import java.lang.Exception

fun SignupData.transformToSignUpData(): SignupRequest{
    return SignupRequest(email, password)
}

fun SignupResponse.transformToDomain(): SignupData{
    return SignupData(email, password)
}

fun SignupData.transformToSignInData(): SigninRequest{
    return SigninRequest(email,password)
}

fun SigninResponse.transformToDomain(): Completable{
    return if (!userToken.isEmpty()){
        Completable.complete()
    }else Completable.error(Exception("Unknown error"))
}

fun NoteResponse.transformToNoteDb(): NoteDb{
    return NoteDb(id = localId, netId = id, title = title, subTitle = subTitle
            , note = note, date = date)
}

fun Note.transformToNoteRequest(): NoteRequest{
    return NoteRequest(id = netId, localId = id, title = title, subTitle = subTitle
            , note = note, date = date)
}