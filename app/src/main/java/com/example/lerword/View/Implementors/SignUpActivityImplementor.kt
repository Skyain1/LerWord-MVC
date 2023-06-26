package com.example.lerword.View.Implementors

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.lerword.Controller.SignUpController
import com.example.lerword.Model.Database.UserDao
import com.example.lerword.Model.Database.UserDatabase
import com.example.lerword.Model.Database.UserRepository
import com.example.lerword.R
import com.example.lerword.View.Activities.LoginActivity
import com.example.lerword.View.Interfaces.SignUpActivityView

/*
 * Created by Skyain1 on 25.06.2023.
 */

class SignUpActivityImplementor (val context: Context, viewGroup: ViewGroup?) : SignUpActivityView{

    var rootView: View
    var controller: SignUpController
    private  var dao: UserDao
    private  var repository: UserRepository

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var enter: Button
    private lateinit var toLogin: TextView

    init {
        rootView= LayoutInflater.from(context).inflate(R.layout.activity_sign_up,viewGroup)
        dao = UserDatabase.getDatabase(context).getUserDao()
        repository = UserRepository(dao)
        controller = SignUpController(repository,this)
    }

    override fun initViews() {
        email = rootView.findViewById(R.id.email)
        password = rootView.findViewById(R.id.password)
        confirmPassword = rootView.findViewById(R.id.confirmpass)
        enter = rootView.findViewById(R.id.enter)
        toLogin = rootView.findViewById(R.id.toLogin)

        enter.setOnClickListener {
            controller.onButtonClicked(
                email = email.text.toString(),
                password = password.text.toString(),
                confirmpas = confirmPassword.text.toString()
            )
        }
        toLogin.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun bindDatatoView() {

    }

    override fun successfulRegistration() {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }

    override fun showErrorToast(error: String) {
        Toast.makeText(rootView.context,error, Toast.LENGTH_SHORT).show()
    }

    override fun getView(): View {
        return rootView
    }
}