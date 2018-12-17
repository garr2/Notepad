package com.garr.pavelbobrovko.data.repositories

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.garr.pavelbobrovko.data.entity.transformToDomain
import com.garr.pavelbobrovko.data.entity.transformToSignInData
import com.garr.pavelbobrovko.data.entity.transformToSignUpData
import com.garr.pavelbobrovko.data.net.SignupService
import com.garr.pavelbobrovko.domain.entity.SignupData
import com.garr.pavelbobrovko.domain.repositories.SignupRepository
import com.garr.pavelbobrovko.domain.utils.PreferenceConst
import io.reactivex.Completable
import io.reactivex.Observable

class SignupRepositoryImpl(private val apiService: SignupService, private val mCtx: Context): SignupRepository {

    private val sPref: SharedPreferences = mCtx.getSharedPreferences(PreferenceConst.DATA_PREFERENCES,MODE_PRIVATE)

    private var token = sPref.getString(PreferenceConst.TOKEN_PREFERENCE,"")

    override fun signUp(signupData: SignupData): Observable<SignupData> {
        return apiService.signUp(signupData.transformToSignUpData())
            .map {
                it.transformToDomain()
            }
    }

    override fun signIn(signupData: SignupData): Completable {
        return apiService.signIn(signupData.transformToSignInData())
            .doOnNext {
                if (it.userToken.isNotEmpty()){
                    val editor = sPref.edit()
                    editor.putString(PreferenceConst.TOKEN_PREFERENCE,it.userToken)
                    editor.putString(PreferenceConst.EMAIL_PREFERENCE, signupData.email)
                    editor.apply()
                    token = it.userToken
                }
            }
            .flatMapCompletable {
                it.transformToDomain()
            }
    }

    override fun getToken(): Observable<String> {
        return Observable.just(token)
    }

    override fun logout(): Completable {
        return if (token.isNotEmpty()){
            apiService.logout(token)
                    .doOnComplete {
                        val ed = sPref.edit()
                        ed.putString(PreferenceConst.TOKEN_PREFERENCE,"")
                        ed.putString(PreferenceConst.EMAIL_PREFERENCE,"")
                        ed.apply()
                    }
        }else Completable.complete()
    }
}