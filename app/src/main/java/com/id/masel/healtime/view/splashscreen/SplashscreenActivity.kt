package com.id.masel.healtime.view.splashscreen

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.id.masel.healtime.databinding.ActivitySplashscreenBinding
import com.id.masel.healtime.util.Constanta.EXTRA_TOKEN
import com.id.masel.healtime.util.Constanta.TIME_SPLASH
import com.id.masel.healtime.util.ViewModelFactory
import com.id.masel.healtime.view.MainActivity
import com.id.masel.healtime.view.login.LoginActivity

class SplashscreenActivity : AppCompatActivity() {

    private lateinit var splashscreenBinding: ActivitySplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashscreenBinding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(splashscreenBinding.root)

        supportActionBar?.hide()

        val handler = Handler(mainLooper)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val splashscreenViewModel: SplashscreenViewModel by viewModels {
            factory
        }
        handler.postDelayed({
            splashscreenViewModel.getUser().observe(this) {
                if (it.isNullOrEmpty()) {
                    Log.e(TAG, "login")
                    startActivity(Intent(this@SplashscreenActivity, LoginActivity::class.java))
                    finish()
                } else {
                    Log.e(TAG, "udah login $it")
                    val intent = Intent(this@SplashscreenActivity, MainActivity::class.java)
                    intent.putExtra(EXTRA_TOKEN, it)
                    startActivity(intent)
                    finish()
                }
            }
        }, TIME_SPLASH)
    }
}