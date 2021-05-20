package com.example.petridelivery

import android.content.Intent
import android.os.Bundle
import com.example.petridelivery.MainActivity
import com.example.petridelivery.abs.BaseActivity
import com.example.petridelivery.wrappers.AuthWrapper
import com.example.petridelivery.wrappers.ConfigWrapper
import com.example.petridelivery.wrappers.base.OnResponseCallback
import com.petri.delivery.web.objects.ContDto
import com.petri.delivery.web.objects.EmployeeType
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var auth: AuthWrapper

    @Inject
    lateinit var config: ConfigWrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wc.inject(this)

        val cont = app.getCont()
        val login = Intent(this, LoginActivity::class.java)

        if (cont == null) {
            startActivity(login)
        } else {
            auth.loginWithToken(cont.accesToken, cont.numeDeUtilizator).enqueue(object : OnResponseCallback<ContDto?>(applicationContext) {
                override fun onSuccessful(response: Response<ContDto?>) {
                    val c: ContDto = response.body()!!
                    app.setCont(cont)

                    config.getConfig().enqueue(object : OnResponseCallback<Hashtable<String?, Any?>?>(applicationContext) {
                        override fun onSuccessful(response: Response<Hashtable<String?, Any?>?>) {
                            app.setConfiguration(response.body())
                            val i = Intent()

                            if (c.getTip() == EmployeeType.LIVRARE) {
                                i.setClass(this@MainActivity, LivratorActivity::class.java)
                            } else {
                                i.setClass(this@MainActivity, ManagementActivity::class.java)
                            }
                            runOnUiThread { startActivity(i) }
                        }
                    })
                }

                override fun onNotSuccesful(response: Response<ContDto?>) {
                    if (response.code() == 401) {
                        login.putExtra(resources.getString(R.string.numeDeUtilizatorExtra), cont.numeDeUtilizator)
                        runOnUiThread { startActivity(login) }
                    }
                }
            })
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }
}