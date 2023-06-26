package com.example.lerword.View.Activities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lerword.View.Implementors.LoginActivityImplementor

/*
 * Created by Skyain1 on 25.06.2023.
 */

class LoginActivity : AppCompatActivity() {

    lateinit var view : LoginActivityImplementor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = LoginActivityImplementor(this@LoginActivity,null)
        setContentView(view.getView())
        view.initViews()
    }

    override fun onResume() {
        super.onResume()
        view.bindDatatoView()
    }
}