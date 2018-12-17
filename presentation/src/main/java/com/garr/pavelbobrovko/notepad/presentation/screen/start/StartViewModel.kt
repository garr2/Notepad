package com.garr.pavelbobrovko.notepad.presentation.screen.start

import android.databinding.ObservableInt
import com.garr.pavelbobrovko.notepad.R
import com.garr.pavelbobrovko.notepad.factories.UseCaseFactory
import com.garr.pavelbobrovko.notepad.presentation.base.BaseViewModel
import com.garr.pavelbobrovko.notepad.presentation.utils.DoubleClickListener
import io.reactivex.rxkotlin.subscribeBy

class StartViewModel: BaseViewModel<StartRouter>() {

    val ibSrc = ObservableInt(R.drawable.ic_cloud_off_white_24dp)

    private var token = ""

    private val getTokenUseCase = UseCaseFactory.provideGetTokenUseCase()
    private val logoutUseCase = UseCaseFactory.provideLogoutUseCase()

    companion object {
        private const val DOUBLE_PRESS_INTERVAL: Long = 750
    }

    init {

        val disposable = getTokenUseCase.getToken()
                .subscribeBy(
                        onNext = {
                            if (it.isNotEmpty()){
                                ibSrc.set(R.drawable.ic_cloud_queue_white_24dp)
                                token = it
                            }
                        },
                        onError = {
                            router?.showError(it)
                        }
                )
        addToDisposable(disposable)
    }

    fun onBackClick(){
        router?.goBack()
    }

    fun onCloudClick(){
        if (token.isEmpty()){
            router?.startSignInActivity()
        }else{
            doubleClickListener.click()
        }
    }

    private val doubleClickListener = object : DoubleClickListener(DOUBLE_PRESS_INTERVAL){

        override fun singleClick() {
            router?.showLogoutMessage()
        }

        override fun doubleClick() {
            val disposable = logoutUseCase.logout()
                    .subscribeBy(
                            onComplete = {
                                ibSrc.set(R.drawable.ic_cloud_off_white_24dp)
                            },
                            onError = {
                                router?.showError(it)
                            }
                    )
            addToDisposable(disposable)
        }

    }
}