package com.garr.pavelbobrovko.notepad.presentation.screen.start.list.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.garr.pavelbobrovko.domain.entity.Note
import com.garr.pavelbobrovko.notepad.databinding.NoteListItemBinding
import com.garr.pavelbobrovko.notepad.presentation.base.recycler.BaseViewHolder

class NoteItemViewHolder(binding: NoteListItemBinding,
                         viewModel: NoteListItemViewModel)
    : BaseViewHolder<Note,NoteListItemViewModel,NoteListItemBinding>(binding,viewModel){

    companion object {
        fun create(parent: ViewGroup, viewModel: NoteListItemViewModel): NoteItemViewHolder{
            val binding = NoteListItemBinding.inflate(LayoutInflater.from(parent.context)
                    ,parent,false)

            return NoteItemViewHolder(binding,viewModel)
        }
    }
}