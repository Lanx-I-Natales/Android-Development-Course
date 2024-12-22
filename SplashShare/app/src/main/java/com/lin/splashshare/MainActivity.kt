package com.lin.splashshare

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lin.splashshare.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("newProjectPref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        binding.apply {
            btnSignup.setOnClickListener {
                val name = etName.text.toString()
                val email = etEmail.text.toString()
                val password = etPass.text.toString()
                val conPassword = etConfirmPass.text.toString()

                if (name != "" && email != "" && password != "" && conPassword != "") {
                    if (password == conPassword) {
                        Toast.makeText(this@MainActivity, "Signup Successful!", Toast.LENGTH_SHORT)
                            .show()
                        editor.putBoolean("sign", true)
                        editor.apply()
                        val intent = Intent(this@MainActivity, LoginActivity::class.java)
                        intent.putExtra("name", name)
                        intent.putExtra("email", email)
                        intent.putExtra("password", password)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@MainActivity, "Password Doesn't Match!", Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@MainActivity, "The fields can't be empty!", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}