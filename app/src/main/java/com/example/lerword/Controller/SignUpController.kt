package com.example.lerword.Controller

import android.util.Patterns
import com.example.lerword.Model.Database.UserRepository
import com.example.lerword.Model.User
import com.example.lerword.View.Implementors.SignUpActivityImplementor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
 * Created by Skyain1 on 25.06.2023.
 */

class SignUpController (private val repository: UserRepository, val View : SignUpActivityImplementor) {

    fun onButtonClicked(email: String, password: String, confirmpas: String) {
        val user = User(email = email, password = password)

        if (validateInput(email, password, confirmpas)) {
            GlobalScope.launch(Dispatchers.Main) {
                withContext(Dispatchers.IO) {
                    repository.insert(user)
                }
                View.successfulRegistration()
            }
        } else {
            View.showErrorToast("Ошибка регистрации")
        }
    }

    private fun validateInput(email: String, password: String, confirmpas: String): Boolean {
        if (email.isNotEmpty() && password.isNotEmpty() && confirmpas.isNotEmpty()
        ) {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                if (password.equals(confirmpas)) {
                    return true
                } else {
                    View.showErrorToast("Пароли не совпадают")
                }
            } else {
                View.showErrorToast("Почта введена не верно")
            }
        }
        return false
    }
}