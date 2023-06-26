package com.example.lerword.Controller

import com.example.lerword.Model.Database.UserRepository
import com.example.lerword.Model.User
import com.example.lerword.View.Implementors.LoginActivityImplementor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
 * Created by Skyain1 on 25.06.2023.
 */

class LoginController (val repository: UserRepository,val view : LoginActivityImplementor ) {

    fun onButtonClicked(user: User){
        GlobalScope.launch(Dispatchers.Main) {
            var isHave = false
            withContext(Dispatchers.IO) {
                isHave = repository.checkField(user)
            }
            if (isHave) {
                view.successfulAuthorization()
            }else{
                view.showErrorToast("Ошибка входа")
            }
        }
    }
}