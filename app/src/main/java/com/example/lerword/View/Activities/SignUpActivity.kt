package com.example.lerword.View.Activities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lerword.View.Implementors.SignUpActivityImplementor

/*
 * Created by Skyain1 on 25.06.2023.
 */

class SignUpActivity : AppCompatActivity() {

    lateinit var view : SignUpActivityImplementor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = SignUpActivityImplementor(this@SignUpActivity,null)
        setContentView(view.getView())
        view.initViews()
    }

    override fun onResume() {
        super.onResume()
        view.bindDatatoView()
    }

}