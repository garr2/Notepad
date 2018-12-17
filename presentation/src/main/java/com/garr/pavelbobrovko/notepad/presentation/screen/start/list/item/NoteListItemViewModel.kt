package com.garr.pavelbobrovko.notepad.presentation.screen.start.list.item

import android.databinding.ObservableField
import com.garr.pavelbobrovko.domain.entity.Note
import com.garr.pavelbobrovko.notepad.presentation.base.recycler.BaseItemViewModel

class NoteListItemViewModel: BaseItemViewModel<Note>() {

    val title = ObservableField<String>("")
    val subTitle = ObservableField<String>("")
    val date = ObservableField<String>("")

    override fun bindItem(item: Note, position: Int) {
        title.set(item.title)
        subTitle.set(item.subTitle)
        date.set(item.date)
    }
}