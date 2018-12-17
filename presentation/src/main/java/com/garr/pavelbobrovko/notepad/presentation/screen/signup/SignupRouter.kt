package com.garr.pavelbobrovko.notepad.presentation.screen.signup

import android.app.Activity
import com.garr.pavelbobrovko.notepad.presentation.base.BaseRouter

class SignupRouter(activity: SignupActivity): BaseRouter<SignupActivity>(activity) {

    fun setResultOkAndFinish(){
        activity.setResult(Activity.RESULT_OK)
        finish()
    }

    fun setResultCancelAndFinish(){
        activity.setResult(Activity.RESULT_CANCELED)
        finish()
    }

    private fun finish(){
        activity.finish()
    }
}