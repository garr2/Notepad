package com.garr.pavelbobrovko.domain.useCases

import com.garr.pavelbobrovko.domain.entity.SignupData
import com.garr.pavelbobrovko.domain.executor.PostExecutorThread
import com.garr.pavelbobrovko.domain.repositories.SignupRepository
import io.reactivex.Completable
import io.reactivex.Observable

class SignUpUseCase(postExecutorThread: PostExecutorThread,
                    private val signupRepository: SignupRepository): BaseUseCase(postExecutorThread) {

    fun signUp(signupData: SignupData): Observable<SignupData>{
        return signupRepository.signUp(signupData)
    }

    fun signIn(signupData: SignupData): Completable{
        return signupRepository.signIn(signupData)
    }
}