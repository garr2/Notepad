package com.garr.pavelbobrovko.notepad.presentation.base.recycler

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import com.garr.pavelbobrovko.notepad.BR

abstract class BaseViewHolder<Entity,
        VM: BaseItemViewModel<Entity>,
        Binding: ViewDataBinding>
(val binding: Binding, val viewModel: VM)
    : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.setVariable(BR.view_model, viewModel)
    }

    fun bind(item: Entity, position: Int) {
        viewModel.bindItem(item, position)
        binding.executePendingBindings()
    }
}