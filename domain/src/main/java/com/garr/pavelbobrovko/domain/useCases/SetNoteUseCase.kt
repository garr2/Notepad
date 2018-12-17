package com.garr.pavelbobrovko.domain.useCases

import com.garr.pavelbobrovko.domain.entity.Note
import com.garr.pavelbobrovko.domain.executor.PostExecutorThread
import com.garr.pavelbobrovko.domain.repositories.NoteRepository
import io.reactivex.Completable

class SetNoteUseCase(
        postExecutorThread: PostExecutorThread,
        private val noteRepository: NoteRepository) : BaseUseCase(postExecutorThread) {

    fun set(note: Note): Completable{
        return noteRepository.set(note)
                .observeOn(postExecutorThread)
                .subscribeOn(workExecutorThread)
    }
}