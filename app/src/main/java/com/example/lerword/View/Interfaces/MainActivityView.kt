package com.example.lerword.View.Interfaces

import com.example.lerword.Model.Word
import com.yuyakaido.android.cardstackview.Direction

/*
 * Created by Skyain1 on 25.06.2023.
 */

interface MainActivityView : MVCView {
    fun bindDatatoView():Unit
    fun getData(words : ArrayList<Word>)
    fun setProgress(knowWords:Int):Unit
    fun showErrorToast(error: String)
    fun setRestart():Unit
}