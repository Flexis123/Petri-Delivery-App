package com.example.petridelivery

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.petridelivery.ChangePasswordActivity
import com.example.petridelivery.abs.BaseActivity
import com.example.petridelivery.wrappers.AuthWrapper
import com.example.petridelivery.wrappers.base.OnResponseCallback
import retrofit2.Response
import javax.inject.Inject

class ChangePasswordActivity : BaseActivity() {
    @kotlin.jvm.JvmField
    @Inject
    var auth: AuthWrapper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wc.inject(this)

        findViewById<View>(R.id.confirmPasswordChangeButton).setOnClickListener { v: View? ->
            val newPassword = (findViewById<View>(R.id.newPasswordEditText) as EditText)
                    .getText().toString()

            val newPasswordRepeated = (findViewById<View>(R.id.newPasswordRepeatedEditText) as EditText)
                    .getText().toString()

            if (newPassword == newPasswordRepeated) {
                val numeDeUtilizator = intent.getStringExtra(resources.getString(R.string.numeDeUtilizatorExtra))

                auth!!.change_default_password(
                        intent.getStringExtra(resources.getString(R.string.parolaExtra)),
                        newPassword,
                        numeDeUtilizator
                ).enqueue(object : OnResponseCallback<Void?>(applicationContext) {
                    override fun onSuccessful(response: Response<Void?>) {
                        val login = Intent(this@ChangePasswordActivity, LoginActivity::class.java)
                        login.putExtra(resources.getString(R.string.numeDeUtilizatorExtra), numeDeUtilizator)
                        startActivity(login)
                    }
                })
            } else if (newPassword.isEmpty()) {
                Toast.makeText(this, R.string.parola_e_goala, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, R.string.parole_diferite, Toast.LENGTH_LONG).show()
            }
        }
        findViewById<View>(R.id.inapoiLaLoginBtn)
                .setOnClickListener { v: View? -> startActivity(Intent(this, LoginActivity::class.java)) }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_change_password
    }
}