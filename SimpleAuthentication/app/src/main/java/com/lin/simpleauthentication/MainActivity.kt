package com.lin.simpleauthentication

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.lin.simpleauthentication.databinding.ActivityMainBinding
import com.lin.simpleauthentication.databinding.CustomAlertDialogBinding
import com.lin.simpleauthentication.databinding.CustomResetPassAlertDialogBinding
import com.lin.simpleauthentication.databinding.CustomTermsDialogBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cBinding: CustomAlertDialogBinding
    private lateinit var bBinding: CustomTermsDialogBinding
    private lateinit var resetBinding: CustomResetPassAlertDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var c = 0
        var userLast = "Lanx"
        var pass = "1234"

        var dialogBuilder=AlertDialog.Builder(this@MainActivity)
        dialogBuilder.setTitle("Invalid Credentials Alert!")
        dialogBuilder.setMessage("Invalid Username or password!")
        dialogBuilder.setCancelable(false)
        dialogBuilder.setPositiveButton("Retry"){_,_-> }
        dialogBuilder.setNegativeButton("Cancel"){_,_->  finish()}
        var dialog=dialogBuilder.create()

        var cDialog= Dialog(this@MainActivity)
        cDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        cDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        cBinding = CustomAlertDialogBinding.inflate(layoutInflater)
        cDialog.setContentView(cBinding.root)
        cBinding.apply {
            alertTitle.text = getString(R.string.logout_alert)
            alertMessage.text = getString(R.string.assurance)
            btnYes.text = getString(R.string.yes)
            btnNo.text = getString(R.string.no)
        }

        var rDialog= Dialog(this@MainActivity)
        rDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        rDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        resetBinding = CustomResetPassAlertDialogBinding.inflate(layoutInflater)
        rDialog.setContentView(resetBinding.root)


        var bDialog= BottomSheetDialog(this@MainActivity)
        bDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        bDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        bBinding = CustomTermsDialogBinding.inflate(layoutInflater)
        bDialog.setContentView(bBinding.root)


        resetBinding.apply {
            btnReset.setOnClickListener(){
                if(etNewPass.text.toString() == etConfirm.text.toString())
                {
                    pass = etNewPass.text.toString()
                    Toast.makeText(this@MainActivity, "Password Change Successful", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(this@MainActivity, "Password Change Unsuccessful", Toast.LENGTH_SHORT).show()
                }
                rDialog.dismiss()
            }
        }


        cBinding.apply {
            btnYes.setOnClickListener(){
                rDialog.show()
                cDialog.dismiss()
            }

            btnNo.setOnClickListener(){
                cDialog.dismiss()
            }
        }

        bBinding.apply {
            btnYes.setOnClickListener(){
                var intent = Intent(this@MainActivity, HomeActivity::class.java)
                intent.putExtra("user", userLast)
                startActivity(intent)
                finish()
                bDialog.dismiss()
            }

            btnNo.setOnClickListener(){
                Toast.makeText(this@MainActivity, "You must accept the terms!", Toast.LENGTH_SHORT).show()
                bDialog.dismiss()
            }
        }

        binding.apply {
            btnLogin.setOnClickListener(){
                var user=eUser.text.toString()
                var password=ePass.text.toString()

                if(user==userLast)
                {
                    if(password==pass)
                    {
                        c=0
                        Toast.makeText(this@MainActivity, "Login Successful!", Toast.LENGTH_SHORT).show()
                        bDialog.show()
                    }
                    else
                    {
                        c++
                        if(c>=3)
                        {
                            cDialog.show()
                        }
                        else
                        {
                            dialog.show()
                        }
                        eUser.text.clear()
                        ePass.text.clear()
                    }
                }
                else
                {
                    c++
                    if(c>=3)
                    {
                        cDialog.show()
                    }
                    else
                    {
                        dialog.show()
                    }
                    eUser.text.clear()
                    ePass.text.clear()
                }
            }
        }
    }
}