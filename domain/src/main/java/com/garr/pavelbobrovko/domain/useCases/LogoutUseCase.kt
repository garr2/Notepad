package com.garr.pavelbobrovko.domain.useCases

import com.garr.pavelbobrovko.domain.executor.PostExecutorThread
import com.garr.pavelbobrovko.domain.repositories.SignupRepository
import io.reactivex.Completable

class LogoutUseCase(postExecutorThread: PostExecutorThread,
                    private val signupRepository: SignupRepository): BaseUseCase(postExecutorThread) {

    fun logout(): Completable{
        return signupRepository.logout()
    }
}