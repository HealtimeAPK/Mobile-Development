@file:Suppress("DEPRECATION")

package com.id.masel.healtime.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.id.masel.healtime.R
import com.id.masel.healtime.view.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val buttonNext: Button = findViewById(R.id.btn_splash)

        buttonNext.setOnClickListener {
            val loginIntent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }
}