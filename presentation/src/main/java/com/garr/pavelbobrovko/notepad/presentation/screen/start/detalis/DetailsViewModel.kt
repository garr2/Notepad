package com.garr.pavelbobrovko.notepad.presentation.screen.start.detalis

import android.databinding.ObservableField
import com.garr.pavelbobrovko.domain.entity.Note
import com.garr.pavelbobrovko.notepad.factories.UseCaseFactory
import com.garr.pavelbobrovko.notepad.presentation.base.BaseViewModel
import com.garr.pavelbobrovko.notepad.presentation.screen.start.StartRouter
import io.reactivex.rxkotlin.subscribeBy
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DetailsViewModel: BaseViewModel<StartRouter>() {

    val etTitle = ObservableField<String>("")
    val etSubTitle = ObservableField<String>("")
    val etNote = ObservableField<String>("")

    var id = ""

    private val getNoteUseCase = UseCaseFactory.provideGetNoteUseCase()
    private val setNoteUseCase = UseCaseFactory.provideSetNoteUseCase()
    private val updateNoteUseCase = UseCaseFactory.provideUpdateNoteUseCase()

    private var netId = ""

    init {
        if (id.isNotEmpty()){
            val disposable = getNoteUseCase.get(id)
                .subscribeBy (
                    onNext = {
                        netId = it.netId
                        etTitle.set(it.title)
                        etSubTitle.set(it.subTitle)
                        etNote.set(it.note)
                    },
                    onError = {
                        router?.showError(it)
                    }
                )
            addToDisposable(disposable)
        }
    }

    fun onSaveClick(){
        val df: DateFormat = SimpleDateFormat("dd.MM.yy")
        addToDisposable(
        if (id.isNotEmpty()){
            updateNoteUseCase.update(Note(id, netId,etTitle.get().toString(),etSubTitle.get().toString()
                ,etNote.get().toString(),df.format(Calendar.getInstance().time)))
                    .subscribeBy(
                            onComplete = {
                                router?.goToListFragment()
                            },
                            onError = {
                                router?.showError(it)
                            }
                    )
        }else{
            setNoteUseCase.set(
                Note("",etTitle.get().toString(),etSubTitle.get().toString()
                    ,etNote.get().toString(),df.format(Calendar.getInstance().time)))
                    .subscribeBy(
                            onComplete = {
                                router?.goToListFragment()
                            },
                            onError = {
                                router?.showError(it)
                            }
                    )
        })

    }
}