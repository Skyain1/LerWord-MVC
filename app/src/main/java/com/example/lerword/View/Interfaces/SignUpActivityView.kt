package com.example.lerword.View.Interfaces

/*
 * Created by Skyain1 on 25.06.2023.
 */

interface SignUpActivityView : MVCView{
    fun bindDatatoView():Unit
    fun successfulRegistration():Unit
    fun showErrorToast(error: String)
}