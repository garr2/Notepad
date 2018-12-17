package com.garr.pavelbobrovko.notepad.app

import android.app.Application

class NoteApp: Application() {

    companion object {
        lateinit var instance: NoteApp
    }

    init {
        instance = this
    }
}