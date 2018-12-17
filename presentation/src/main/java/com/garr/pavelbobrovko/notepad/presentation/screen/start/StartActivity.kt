package com.garr.pavelbobrovko.notepad.presentation.screen.start

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import com.garr.pavelbobrovko.notepad.R
import com.garr.pavelbobrovko.notepad.databinding.ActivityStartBinding
import com.garr.pavelbobrovko.notepad.presentation.base.BaseMvvmActivity
import com.garr.pavelbobrovko.notepad.presentation.screen.signup.SignupActivity

import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : BaseMvvmActivity<StartViewModel, StartRouter, ActivityStartBinding>() {

    companion object {
        private const val REQUEST_CODE = 1
    }

    override fun prodiveViewModel(): StartViewModel {
        return ViewModelProviders.of(this).get(StartViewModel::class.java)
    }

    override fun provideRouter(): StartRouter {
        return StartRouter(this)
    }

    override fun provideLayoutId(): Int {
        return R.layout.activity_start
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null){
            router.goToListFragment()
        }
    }

    fun goToSignInActivity(){
        startActivityForResult(Intent(this, SignupActivity::class.java), REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){

        }
    }

}
