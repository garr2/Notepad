package com.garr.pavelbobrovko.notepad.presentation.screen.start.list

import com.garr.pavelbobrovko.domain.entity.Note
import com.garr.pavelbobrovko.notepad.factories.UseCaseFactory
import com.garr.pavelbobrovko.notepad.presentation.base.BaseViewModel
import com.garr.pavelbobrovko.notepad.presentation.screen.start.StartRouter
import com.garr.pavelbobrovko.notepad.presentation.screen.start.list.item.NoteListAdapter
import io.reactivex.rxkotlin.subscribeBy

class ListViewModel: BaseViewModel<StartRouter>() {

    val adapter = NoteListAdapter()

    private val noteListUseCase = UseCaseFactory.provideGetNoteListUseCase()


    init {

        val disposable = noteListUseCase.getList()
                .subscribeBy (
                        onNext = {
                            adapter.itemList = it
                            adapter.notifyDataSetChanged()
                        },
                        onError = {
                            router?.showError(it)
                        }
                )

        addToDisposable(disposable)

        adapter.onItemClickListener = object : NoteListAdapter.OnItemClickListener{
            override fun onClick(note: Note) {
                router?.goToDetalisFragment(note.id)
            }
        }
    }

    fun onAddNewClick(){
        router?.goToDetalisFragment("")
    }
}