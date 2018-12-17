package com.garr.pavelbobrovko.data.net

import com.garr.pavelbobrovko.data.entity.SigninRequest
import com.garr.pavelbobrovko.data.entity.SigninResponse
import com.garr.pavelbobrovko.data.entity.SignupRequest
import com.garr.pavelbobrovko.data.entity.SignupResponse
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*

interface SignupApi {

    @POST("users/register")
    @Headers("Content-Type:application/json")
    fun signUp(@Body signupRequest: SignupRequest): Observable<SignupResponse>

    @POST("users/login")
    @Headers("Content-Type:application/json")
    fun signIn(@Body signInRequest: SigninRequest): Observable<SigninResponse>

    @GET("users/logout")
    fun logout(@Query("user-token")token: String): Completable
}