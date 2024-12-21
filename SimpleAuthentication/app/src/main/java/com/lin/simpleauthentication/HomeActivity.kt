package com.lin.simpleauthentication


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.lin.simpleauthentication.databinding.ActivityCardBinding
import com.lin.simpleauthentication.databinding.CustomAlertDialogBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCardBinding
    private lateinit var bindAlert: CustomAlertDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var name ="Welcome " + intent.getStringExtra("user")

        binding.title.text = name

        binding.btnExit.setOnClickListener(){

            var dialog = BottomSheetDialog(this@HomeActivity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            bindAlert = CustomAlertDialogBinding.inflate(layoutInflater)
            dialog.setContentView(bindAlert.root)

            bindAlert.apply {
                alertTitle.text="Alert!"
                alertMessage.text="Are you sure you want to exit?"
                btnYes.text="Yes"
                btnNo.text="No"

                btnYes.setOnClickListener()
                {
                    dialog.dismiss()
                    finish()
                }

                btnNo.setOnClickListener()
                {
                    dialog.dismiss()
                }
            }

            dialog.show()
        }
    }
}