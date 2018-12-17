package com.garr.pavelbobrovko.data.net

import com.garr.pavelbobrovko.data.entity.SigninRequest
import com.garr.pavelbobrovko.data.entity.SigninResponse
import com.garr.pavelbobrovko.data.entity.SignupRequest
import com.garr.pavelbobrovko.data.entity.SignupResponse
import com.garr.pavelbobrovko.data.net.base.BaseRestService
import io.reactivex.Completable
import io.reactivex.Observable

class SignupService(apiUrl: String): BaseRestService(apiUrl) {

    private lateinit var restApi: SignupApi

    override fun buildApiClass() {
        restApi = retrofit.create(SignupApi::class.java)
    }

    fun signUp(signupRequest: SignupRequest): Observable<SignupResponse>{
        return restApi.signUp(signupRequest)
    }

    fun signIn(signinRequest: SigninRequest): Observable<SigninResponse>{
        return restApi.signIn(signinRequest)
    }

    fun logout(token: String): Completable{
        return restApi.logout(token)
    }
}