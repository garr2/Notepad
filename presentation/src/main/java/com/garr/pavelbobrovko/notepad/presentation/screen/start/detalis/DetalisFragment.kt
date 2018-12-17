package com.garr.pavelbobrovko.notepad.presentation.screen.start.detalis

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.garr.pavelbobrovko.notepad.R
import com.garr.pavelbobrovko.notepad.databinding.DetalisFragmentBinding
import com.garr.pavelbobrovko.notepad.presentation.base.BaseMvvmFragment
import com.garr.pavelbobrovko.notepad.presentation.screen.start.StartRouter

class DetalisFragment: BaseMvvmFragment<DetailsViewModel, StartRouter, DetalisFragmentBinding>() {

    companion object {
        private const val ID_EXTRA = "ID_EXTRA"
        fun getInstance(id: String): DetalisFragment{
            val fragment = DetalisFragment()
            val arguments = Bundle()
            arguments.putString(ID_EXTRA,id)
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun prodiveViewModel(): DetailsViewModel {
        return ViewModelProviders.of(this).get(DetailsViewModel::class.java)
    }

    override fun provideLayoutId(): Int {
        return R.layout.detalis_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments!=null){
            val id = arguments?.getString(ID_EXTRA) ?: ""
            if (id.isEmpty()){
                router?.goBack()
            }else viewModel.id = id
        }
    }
}