package com.garr.pavelbobrovko.notepad.presentation.screen.start.list.item

import android.view.ViewGroup
import com.garr.pavelbobrovko.domain.entity.Note
import com.garr.pavelbobrovko.notepad.databinding.NoteListItemBinding
import com.garr.pavelbobrovko.notepad.presentation.base.recycler.BaseRecyclerAdapter
import com.garr.pavelbobrovko.notepad.presentation.base.recycler.BaseViewHolder
import io.reactivex.rxkotlin.subscribeBy

class NoteListAdapter: BaseRecyclerAdapter<Note,NoteListItemViewModel>() {

    var onItemClickListener: OnItemClickListener? = null

    init {
        clickItemSubject.subscribeBy {
            onItemClickListener?.onClick(it.item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): BaseViewHolder<Note
            , NoteListItemViewModel, NoteListItemBinding> {
        return NoteItemViewHolder.create(parent, NoteListItemViewModel())
    }

    interface OnItemClickListener{
        fun onClick(note: Note)
    }
}