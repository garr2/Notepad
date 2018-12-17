package com.garr.pavelbobrovko.domain.repositories

import com.garr.pavelbobrovko.domain.entity.SignupData
import io.reactivex.Completable
import io.reactivex.Observable

interface SignupRepository: BaseRepository {

    fun signUp(signupData: SignupData): Observable<SignupData>

    fun signIn(signupData: SignupData): Completable

    fun getToken(): Observable<String>

    fun logout(): Completable
}