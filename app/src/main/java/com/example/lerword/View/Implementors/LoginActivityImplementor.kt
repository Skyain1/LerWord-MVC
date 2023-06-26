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
import com.example.lerword.Controller.LoginController
import com.example.lerword.Model.Database.UserDao
import com.example.lerword.Model.Database.UserDatabase
import com.example.lerword.Model.Database.UserRepository
import com.example.lerword.Model.User
import com.example.lerword.R
import com.example.lerword.View.Activities.MainActivity
import com.example.lerword.View.Activities.SignUpActivity
import com.example.lerword.View.Interfaces.LoginActivityView

/*
 * Created by Skyain1 on 25.06.2023.
 */

class LoginActivityImplementor (val context: Context,viewGroup: ViewGroup?) : LoginActivityView {

    private var rootView: View
    private var controller: LoginController
    private  var dao: UserDao
    private  var repository: UserRepository

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var enter: Button
    private lateinit var toSignUp: TextView

    init {
        rootView= LayoutInflater.from(context).inflate(R.layout.activity_login,viewGroup)
        dao = UserDatabase.getDatabase(context).getUserDao()
        repository = UserRepository(dao)
        controller = LoginController(repository,this)
    }
    override fun initViews() {
        email = rootView.findViewById(R.id.email)
        password = rootView.findViewById(R.id.password)
        enter = rootView.findViewById(R.id.enter)
        toSignUp = rootView.findViewById(R.id.toSignUp)

        enter.setOnClickListener {
            val user = User(email = email.text.toString(), password = password.text.toString())
            controller.onButtonClicked(user)
        }
        toSignUp.setOnClickListener {
            val intent = Intent(context, SignUpActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun bindDatatoView() {

    }

    override fun successfulAuthorization() {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }

    override fun showErrorToast(error: String) {
        Toast.makeText(rootView.context,error,Toast.LENGTH_SHORT).show()
    }

    override fun getView(): View {
      return rootView
    }
}