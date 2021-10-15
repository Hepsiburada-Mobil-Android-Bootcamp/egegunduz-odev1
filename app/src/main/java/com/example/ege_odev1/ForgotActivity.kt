package com.example.ege_odev1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class ForgotActivity : AppCompatActivity() {

    var viewModel : ForgotViewModel = ForgotViewModel()

    private val sendButton: View by lazy { findViewById<View>(R.id.send_button) }
    private val textView: TextView by lazy { findViewById<TextView>(R.id.forgot_textView) }
    private val editText: EditText by lazy { findViewById<EditText>(R.id.editText_email) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        viewModel = ViewModelProvider(this).get(ForgotViewModel::class.java)

        viewModel.currentEmail?.observe(this, Observer {
            val validStatus =  isValidEmail(it)
            getValidationText(it, validStatus)
            makeToast(it, validStatus)
            }

        )

        sendMail()
    }

    private fun makeToast(email: String, validStatus: Boolean) {
        when(validStatus) {
            true -> Toast.makeText(applicationContext, getString(R.string.sent_text, email), Toast.LENGTH_LONG).show()
            false -> Toast.makeText(applicationContext, R.string.failed, Toast.LENGTH_LONG).show()
        }
    }

    private fun getValidationText(email: String, validStatus: Boolean) {
        when(validStatus) {
            true -> textView.text = getString(R.string.valid_address, email)
            false -> textView.text = getString(R.string.invalid_address, email)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun sendMail() {
        sendButton.setOnClickListener {
            viewModel.currentEmail?.value = getEditTextValue()
        }
    }

    private fun getEditTextValue(): String {
        return editText.text.toString()
    }
}