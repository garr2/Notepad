package com.garr.pavelbobrovko.notepad.factories

import com.garr.pavelbobrovko.data.db.AppDatabase
import com.garr.pavelbobrovko.data.net.SignupService
import com.garr.pavelbobrovko.data.repositories.NoteRepositoryImpl
import com.garr.pavelbobrovko.data.repositories.SignupRepositoryImpl
import com.garr.pavelbobrovko.domain.useCases.*
import com.garr.pavelbobrovko.notepad.BuildConfig
import com.garr.pavelbobrovko.notepad.app.NoteApp
import com.garr.pavelbobrovko.notepad.executor.UIThread

object UseCaseFactory {

    private val uiThread = UIThread()
    private val noteDao = AppDatabase.getInstance(NoteApp.instance).getNoteDao()
    private val deffaultApiUrl = (BuildConfig.BASE_API_URL + "/" + BuildConfig.APPLICATION_ID
            + "/" + BuildConfig.BASE_API_URL + "/")

    private val noteRepositoryImpl = NoteRepositoryImpl(noteDao)
    private val signupService = SignupService(deffaultApiUrl)
    private val signupRepositoryImpl = SignupRepositoryImpl(signupService, NoteApp.instance)

    fun provideDeleteNoteUseCase(): DeleteNoteUseCase{
        return DeleteNoteUseCase(uiThread, noteRepositoryImpl)
    }

    fun provideGetNoteListUseCase(): GetNoteListUseCase{
        return GetNoteListUseCase(uiThread, noteRepositoryImpl)
    }

    fun provideGetNoteUseCase(): GetNoteUseCase{
        return GetNoteUseCase(uiThread, noteRepositoryImpl)
    }

    fun provideSetNoteUseCase(): SetNoteUseCase{
        return SetNoteUseCase(uiThread, noteRepositoryImpl)
    }

    fun provideUpdateNoteUseCase(): UpdateNoteUseCase{
        return UpdateNoteUseCase(uiThread, noteRepositoryImpl)
    }

    fun provideSignUpUseCase(): SignUpUseCase{
        return SignUpUseCase(uiThread, signupRepositoryImpl)
    }

    fun provideGetTokenUseCase(): GetTokenUseCase{
        return GetTokenUseCase(uiThread, signupRepositoryImpl)
    }

    fun provideLogoutUseCase(): LogoutUseCase{
        return LogoutUseCase(uiThread, signupRepositoryImpl)
    }
}