package com.garr.pavelbobrovko.domain.useCases

import com.garr.pavelbobrovko.domain.entity.Note
import com.garr.pavelbobrovko.domain.executor.PostExecutorThread
import com.garr.pavelbobrovko.domain.repositories.NoteRepository
import io.reactivex.Flowable

class GetNoteUseCase(
        postExecutorThread: PostExecutorThread,
        private val noteRepository: NoteRepository) : BaseUseCase(postExecutorThread){

    fun get(id: String): Flowable<Note>{
        return noteRepository.get(id)
                .observeOn(postExecutorThread)
                .subscribeOn(workExecutorThread)
    }
}