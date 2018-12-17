package com.garr.pavelbobrovko.notepad.presentation.utils

import android.graphics.Rect
import android.support.constraint.ConstraintLayout
import android.view.ViewTreeObserver

abstract class OpenKeyboardListener(private val rootLayout: ConstraintLayout): ViewTreeObserver.OnGlobalLayoutListener {

    override fun onGlobalLayout() {

        val r = Rect()
        rootLayout.getWindowVisibleDisplayFrame(r)
        val screenHeight = rootLayout.rootView.height
        val keypadHeight = screenHeight - r.bottom

        if(keypadHeight > screenHeight * 0.15){
            onKeyboardShow()
        }else onKeyboardHide()
    }

    abstract fun onKeyboardShow()

    abstract fun onKeyboardHide()
}