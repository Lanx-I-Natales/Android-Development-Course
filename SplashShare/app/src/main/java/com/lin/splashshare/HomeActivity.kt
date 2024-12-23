package com.lin.splashshare

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lin.splashshare.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences("SplashSharePref", Context.MODE_PRIVATE)
        var editor = sharedPref.edit()

        var name: String

        if (sharedPref.getBoolean("firstTime", false)) {
            name = "Welcome " + sharedPref.getString("name", "")
            editor.putBoolean("firstTime", false)
            editor.apply()
        } else {
            name = "Welcome back, " + sharedPref.getString("name", "")
        }

        val email = "Your Email: " + sharedPref.getString("email", "")

        binding.txtName.text = name
        binding.txtEmail.text = email
    }

}
