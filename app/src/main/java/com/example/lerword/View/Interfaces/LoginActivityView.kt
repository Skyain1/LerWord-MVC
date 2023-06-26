package com.example.lerword.View.Interfaces

/*
 * Created by Skyain1 on 25.06.2023.
 */

interface LoginActivityView : MVCView{
    fun bindDatatoView():Unit
    fun successfulAuthorization():Unit
    fun showErrorToast(error: String)
}