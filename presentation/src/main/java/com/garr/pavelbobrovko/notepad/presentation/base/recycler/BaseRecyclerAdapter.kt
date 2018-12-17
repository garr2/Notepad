package com.garr.pavelbobrovko.notepad.presentation.base.recycler

import android.support.v7.widget.RecyclerView
import io.reactivex.subjects.PublishSubject

abstract class BaseRecyclerAdapter<
        Entity,
        VM: BaseItemViewModel<Entity>>
    : RecyclerView.Adapter<BaseViewHolder<Entity, VM, *>>(){

    val clickItemSubject = PublishSubject.create<ItemClick<Entity>>()

    var itemList = ArrayList<Entity>()

    override fun onBindViewHolder(holder: BaseViewHolder<Entity, VM, *>, position: Int) {
        holder.bind(itemList[position], position)
    }

    override fun getItemCount(): Int = itemList.size

    fun addItems(items: List<Entity>) {
        val startPos = itemList.size
        itemList.addAll(items)
        notifyItemRangeChanged(startPos, items.size)
    }

    fun clearItems() {
        itemList.clear()
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<Entity, VM, *>) {
        super.onViewAttachedToWindow(holder)

        holder.itemView.setOnClickListener{
            val pos = holder.adapterPosition
            clickItemSubject.onNext(ItemClick(itemList[pos], pos))
            holder.viewModel.onItemClick()
        }
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<Entity, VM, *>) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.setOnClickListener(null)
    }
}