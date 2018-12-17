package com.garr.pavelbobrovko.domain.useCases

import com.garr.pavelbobrovko.domain.entity.Note
import com.garr.pavelbobrovko.domain.executor.PostExecutorThread
import com.garr.pavelbobrovko.domain.repositories.NoteRepository
import io.reactivex.Flowable

class GetNoteListUseCase(
        postExecutorThread: PostExecutorThread,
        private val noteRepository: NoteRepository) : BaseUseCase(postExecutorThread) {

    fun getList(): Flowable<ArrayList<Note>>{
        return noteRepository.getList()
                .observeOn(postExecutorThread)
                .subscribeOn(workExecutorThread)
    }
}