package com.garr.pavelbobrovko.domain.useCases

import com.garr.pavelbobrovko.domain.entity.Note
import com.garr.pavelbobrovko.domain.executor.PostExecutorThread
import com.garr.pavelbobrovko.domain.repositories.NoteRepository
import io.reactivex.Completable

class DeleteNoteUseCase(
        postExecutorThread: PostExecutorThread,
        private val noteRepository: NoteRepository) : BaseUseCase(postExecutorThread) {

    fun delete(note: Note): Completable{
        return noteRepository.delete(note)
                .observeOn(postExecutorThread)
                .subscribeOn(workExecutorThread)
    }
}