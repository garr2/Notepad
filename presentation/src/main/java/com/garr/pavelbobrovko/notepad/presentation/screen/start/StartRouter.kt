package com.garr.pavelbobrovko.notepad.presentation.screen.start

import android.content.Intent
import android.widget.Toast
import com.garr.pavelbobrovko.notepad.R
import com.garr.pavelbobrovko.notepad.presentation.base.BaseRouter
import com.garr.pavelbobrovko.notepad.presentation.screen.signup.SignupActivity
import com.garr.pavelbobrovko.notepad.presentation.screen.start.detalis.DetalisFragment
import com.garr.pavelbobrovko.notepad.presentation.screen.start.list.ListFragment

class StartRouter(activity: StartActivity): BaseRouter<StartActivity>(activity) {

    fun goToListFragment(){
        replaceFragment(activity.supportFragmentManager,ListFragment.getInstance()
                , R.id.flFragment, false)
    }

    fun goToDetalisFragment(id: String){
        replaceFragment(activity.supportFragmentManager, DetalisFragment.getInstance(id)
                , R.id.flFragment, true)
    }

    fun startSignInActivity(){
        activity.goToSignInActivity()
    }

    fun showLogoutMessage(){
        Toast.makeText(activity,activity.getString(R.string.logout_click), Toast.LENGTH_SHORT).show()
    }
}