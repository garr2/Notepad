package com.garr.pavelbobrovko.domain.useCases

import com.garr.pavelbobrovko.domain.entity.SignupData
import com.garr.pavelbobrovko.domain.executor.PostExecutorThread
import com.garr.pavelbobrovko.domain.repositories.SignupRepository
import io.reactivex.Observable

class GetTokenUseCase(postExecutorThread: PostExecutorThread
                      , private val signupRepository: SignupRepository): BaseUseCase(postExecutorThread) {

    fun getToken(): Observable<String>{
        return signupRepository.getToken()
    }
}