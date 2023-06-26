package com.example.lerword.View.Activities
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lerword.View.Implementors.MainActivityImplementor

/*
 * Created by Skyain1 on 25.06.2023.
 */

class MainActivity : AppCompatActivity() {

    lateinit var view : MainActivityImplementor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = MainActivityImplementor(this@MainActivity,null)
        setContentView(view.getView())
        view.initViews()
    }
    override fun onResume() {
        super.onResume()
        view.bindDatatoView()
    }

}