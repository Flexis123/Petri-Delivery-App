package com.example.petridelivery

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.petridelivery.abs.BaseActivity
import com.example.petridelivery.wrappers.AuthWrapper
import com.example.petridelivery.wrappers.base.OnResponseCallback
import com.petri.delivery.web.objects.ContDto
import retrofit2.Response
import javax.inject.Inject

class LoginActivity : BaseActivity() {
    @kotlin.jvm.JvmField
    @Inject
    var auth: AuthWrapper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wc.inject(this)

        val numeUtilizator = resources.getString(R.string.numeDeUtilizatorExtra)

        val numeDeUtilizator = intent.getStringExtra(numeUtilizator)
        val numeDeUtilizatorEditText = findViewById<EditText>(R.id.numeDeUtilizatorEditText)

        if (numeDeUtilizator != null) {
            numeDeUtilizatorEditText.setText(numeDeUtilizator)
        }
        findViewById<View>(R.id.loginButton).setOnClickListener(View.OnClickListener { v: View? ->
            val usr = numeDeUtilizatorEditText.getText().toString()
            val numeDeUtilizator1 = if (usr == numeDeUtilizator) numeDeUtilizator else usr
            val parola = (findViewById<View?>(R.id.parolaEditText) as EditText?)
                    ?.getText().toString()

            auth?.login(parola, numeDeUtilizator1)!!.enqueue(object : OnResponseCallback<ContDto?>(applicationContext) {
                override fun onSuccessful(response: Response<ContDto?>) {
                    val cont = response.body()
                    app.setCont(cont)
                    runOnUiThread { startActivity(Intent(this@LoginActivity, MainActivity::class.java)) }
                }

                override fun onNotSuccesful(response: Response<ContDto?>) {
                    if (response.code() == 308) {
                        val changePassword = Intent(this@LoginActivity, ChangePasswordActivity::class.java)

                        changePassword.putExtra(numeUtilizator, numeDeUtilizator1)
                        changePassword.putExtra(resources.getString(R.string.parolaExtra), parola)

                        runOnUiThread { startActivity(changePassword) }
                    }
                }
            })
        })
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }
}