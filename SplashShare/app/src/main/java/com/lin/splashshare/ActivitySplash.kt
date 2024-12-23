package com.lin.splashshare

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class ActivitySplash : AppCompatActivity() {
    private lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPref = getSharedPreferences("SplashSharePref", Context.MODE_PRIVATE)

        val signup = sharedPref.getBoolean("sign", false)
        val login = sharedPref.getBoolean("log", false)

        Handler(Looper.getMainLooper()).postDelayed({
            if (signup) {
                if (login) {
                    startActivity(Intent(this@ActivitySplash, HomeActivity::class.java))
                    finish()
                } else {
                    startActivity(Intent(this@ActivitySplash, LoginActivity::class.java))
                    finish()
                }
            } else {
                startActivity(Intent(this@ActivitySplash, MainActivity::class.java))
                finish()
            }
        }, 3000)

    }
}