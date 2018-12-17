package com.garr.pavelbobrovko.notepad.presentation.screen.signup

import android.animation.ValueAnimator
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.garr.pavelbobrovko.notepad.R
import com.garr.pavelbobrovko.notepad.databinding.ActivitySignupBinding
import com.garr.pavelbobrovko.notepad.presentation.base.BaseMvvmActivity
import com.garr.pavelbobrovko.notepad.presentation.utils.OpenKeyboardListener

class SignupActivity : BaseMvvmActivity<SignupViewModel, SignupRouter, ActivitySignupBinding>() {

    private var keyboardListenersAttached = false
    private var isViewMoveUp = false

    private lateinit var keyboardListener: OpenKeyboardListener

    override fun prodiveViewModel(): SignupViewModel {
        return ViewModelProviders.of(this).get(SignupViewModel::class.java)
    }

    override fun provideRouter(): SignupRouter {
        return SignupRouter(this)
    }

    override fun provideLayoutId(): Int {
       return R.layout.activity_signup
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun attachKeyboardListener(){
        if (keyboardListenersAttached)return

        binding.clRootLayout.viewTreeObserver.addOnGlobalLayoutListener(keyboardListener)

        keyboardListenersAttached = true
    }

    override fun onDestroy() {
        super.onDestroy()
        if (keyboardListenersAttached){
            binding.clRootLayout.viewTreeObserver.removeOnGlobalLayoutListener(keyboardListener)
        }
    }

    private fun initKeyboardListener(){
        keyboardListener = object : OpenKeyboardListener(binding.clRootLayout){

            override fun onKeyboardShow() {
                if (!isViewMoveUp){

                    val animator = ValueAnimator.ofFloat(0f, -220f)
                    animator.duration = 750
                    animator.start()

                    animator.addUpdateListener { animation ->
                        val animatedValue = animation.animatedValue as Float
                        binding.tvEmail.translationY = animatedValue
                        binding.etEmail.translationY = animatedValue
                        binding.tvPass.translationY = animatedValue
                        binding.etPass.translationY = animatedValue
                        binding.btnRegister.translationY = animatedValue
                    }

                    isViewMoveUp = true
                }
            }

            override fun onKeyboardHide() {
                if (isViewMoveUp){

                    val animator = ValueAnimator.ofFloat(-220f, 0f)
                    animator.duration = 750
                    animator.start()

                    animator.addUpdateListener { animation ->
                        val animatedValue = animation.animatedValue as Float
                        binding.tvEmail.translationY = animatedValue
                        binding.etEmail.translationY = animatedValue
                        binding.tvPass.translationY = animatedValue
                        binding.etPass.translationY = animatedValue
                        binding.btnRegister.translationY = animatedValue
                    }

                    isViewMoveUp = false
                }
            }

        }
    }

}
