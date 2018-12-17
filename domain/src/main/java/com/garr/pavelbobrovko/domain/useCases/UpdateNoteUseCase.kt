package com.garr.pavelbobrovko.domain.useCases

import com.garr.pavelbobrovko.domain.entity.Note
import com.garr.pavelbobrovko.domain.executor.PostExecutorThread
import com.garr.pavelbobrovko.domain.repositories.NoteRepository
import io.reactivex.Completable

class UpdateNoteUseCase(
        postExecutorThread: PostExecutorThread,
        private val noteRepository: NoteRepository) : BaseUseCase(postExecutorThread) {

    fun update(note: Note): Completable{
        return noteRepository.update(note)
                .observeOn(postExecutorThread)
                .subscribeOn(workExecutorThread)
    }
}