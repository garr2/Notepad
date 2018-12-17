package com.garr.pavelbobrovko.notepad.presentation.screen.signup

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.garr.pavelbobrovko.domain.entity.SignupData
import com.garr.pavelbobrovko.notepad.factories.UseCaseFactory
import com.garr.pavelbobrovko.notepad.presentation.base.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy

class SignupViewModel: BaseViewModel<SignupRouter>() {

    val etEmail = ObservableField<String>("")
    val etPass = ObservableField<String>("")
    val cbCreateNew = ObservableBoolean(false)

    private val signUpUseCase = UseCaseFactory.provideSignUpUseCase()
    private val getTokenUseCase = UseCaseFactory.provideGetTokenUseCase()

    init {
        val disposable = getTokenUseCase.getToken()
            .subscribeBy(
                onNext = {
                    if (it.isEmpty()){
                        cbCreateNew.set(true)
                    }
                },
                onError = {
                    router?.showError(it)
                }
            )
        addToDisposable(disposable)
    }

    fun onSignInClick(){
        if (cbCreateNew.get()){
            signIn()
        }else{
            signUp()
        }
    }

    fun signUp(){
        val disposable = signUpUseCase.signUp(SignupData(etEmail.get().toString(),etPass.get().toString()))
            .subscribeBy(
                onNext = {
                    signIn()
                },
                onError = {
                    router?.showError(it)
                }
            )
        addToDisposable(disposable)
    }

    fun signIn(){
        val disposable = signUpUseCase.signIn(SignupData(etEmail.get().toString(),etPass.get().toString()))
            .subscribeBy(
                onComplete = {
                    router?.setResultOkAndFinish()
                },
                onError = {
                    router?.showError(it)
                }
            )

        addToDisposable(disposable)
    }

}