package com.example.lerword.View.Interfaces

import android.view.View

/*
 * Created by Skyain1 on 26.06.2023.
 */

interface CardItemView <T> {
    interface ButtonClickListener  {
        fun onButtonRightClick()
        fun onButtonLeftClick()
    }

    fun setCardButtonClickListener(listener:ButtonClickListener)
    fun getView(): View
    fun initViews()
    fun bindDataToView(unit : T)

}