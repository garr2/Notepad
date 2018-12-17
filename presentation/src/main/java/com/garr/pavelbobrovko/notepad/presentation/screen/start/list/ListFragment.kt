package com.garr.pavelbobrovko.notepad.presentation.screen.start.list

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.garr.pavelbobrovko.notepad.R
import com.garr.pavelbobrovko.notepad.databinding.ListFragmentBinding
import com.garr.pavelbobrovko.notepad.presentation.base.BaseMvvmFragment
import com.garr.pavelbobrovko.notepad.presentation.screen.start.StartRouter

class ListFragment: BaseMvvmFragment<ListViewModel, StartRouter, ListFragmentBinding>() {

    companion object {
        private const val ID_EXTRA = "ID_EXTRA"
        fun getInstance(): ListFragment{
            return ListFragment()
        }
    }

    override fun prodiveViewModel(): ListViewModel {
        return ViewModelProviders.of(this).get(ListViewModel::class.java)
    }

    override fun provideLayoutId(): Int {
        return R.layout.list_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            binding.rvList.adapter = viewModel.adapter
            binding.rvList.layoutManager = LinearLayoutManager(context)
            binding.rvList.setHasFixedSize(true)
    }
}