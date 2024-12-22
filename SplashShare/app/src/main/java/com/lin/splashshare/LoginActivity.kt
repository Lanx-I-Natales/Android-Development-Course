package com.lin.splashshare

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lin.splashshare.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("newProjectPref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")

        binding.apply {
            btnLogin.setOnClickListener {
                val newEmail = etEmail.text.toString()
                val newPassword = etPass.text.toString()

                if (newEmail != "" && password != "") {
                    if (newEmail == email) {
                        if (password == newPassword) {
                            Toast.makeText(
                                this@LoginActivity, "Login Successful!", Toast.LENGTH_SHORT
                            ).show()
                            editor.putBoolean("login", true)
                            editor.apply()
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            intent.putExtra("name", name)
                            intent.putExtra("email", email)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this@LoginActivity, "Invalid Password!", Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@LoginActivity, "Email doesn't exist!", Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@LoginActivity, "The fields can't be empty!", Toast.LENGTH_SHORT
                    ).show()
                }

                btnSignup.setOnClickListener()
                {
                    etEmail.text.clear()
                    etPass.text.clear()
                    startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                }
            }
        }
    }
}