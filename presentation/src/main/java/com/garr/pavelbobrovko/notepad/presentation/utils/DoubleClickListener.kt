package com.garr.pavelbobrovko.notepad.presentation.utils

abstract class DoubleClickListener(val PRESS_INTERVAL: Long) {

    private var currentClick: Long = 0

    fun click(){
        val pressTime = System.currentTimeMillis()

        if (pressTime - currentClick >= PRESS_INTERVAL){
            singleClick()
        }else doubleClick()

        currentClick = pressTime
    }

    abstract fun singleClick()
    abstract fun doubleClick()
}