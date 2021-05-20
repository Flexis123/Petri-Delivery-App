package com.example.petridelivery.wrappers

import com.example.petridelivery.ChangePasswordActivity
import com.example.petridelivery.LoginActivity
import com.example.petridelivery.MainActivity
import com.example.petridelivery.app.PetriDeliveryApp
import com.example.petridelivery.fragments.ClientiFragment
import com.example.petridelivery.wrappers.base.WrappersModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [WrappersModule::class])
@Singleton
interface WrapperComponent {
    fun getConfigWrapper(): ConfigWrapper
    fun app(): PetriDeliveryApp
    fun inject(app: PetriDeliveryApp)
    fun inject(loginActivity: LoginActivity)
    fun inject(changePasswordActivity: ChangePasswordActivity)
    fun inject(mainActivity: MainActivity)
    fun inject(clientiFragment: ClientiFragment)
}