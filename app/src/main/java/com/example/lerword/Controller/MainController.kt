package com.example.lerword.Controller

import com.example.lerword.Model.Database.WordsRepository
import com.example.lerword.Model.Word
import com.example.lerword.View.Implementors.MainActivityImplementor
import com.yuyakaido.android.cardstackview.Direction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
 * Created by Skyain1 on 25.06.2023.
 */

class MainController (private val repository: WordsRepository, val view : MainActivityImplementor){

    private var words = ArrayList<Word>()
    private var fullwords = ArrayList<Word>()
    private var curword =0
    private var knowWords =0
    private var swipe = 0

    fun cardSwipe(direction: Direction?){
        if (direction == Direction.Right) {
            if (swipe == 0) {
                words[10] = words[curword]
                swipe++
            }
        } else if (direction == Direction.Left) {
            updateKnow(words[curword])
        }
        iteration()
    }

     fun setTenWords() {
        curword = 0
        swipe = 0
        words.clear()
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                fullwords = repository.getWords()
            }
            repeat(11) {
                val word = fullwords.find { !it.know && !words.contains(it) }
                if (word != null) {
                    words.add(word)
                }
            }
            view.getData(words)
        }
    }

    private fun updateKnow(word: Word) {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                repository.setKnow(word)
            }
            knowWords++
            if(knowWords==10){
                view.setProgress(knowWords)
                view.setRestart()
                knowWords=0
            }else{
                view.setProgress(knowWords)
            }
        }
    }

    private fun updateCount(word: Word) {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                repository.updateField(word)
            }
        }
    }

    private fun iteration() {
        updateCount(words[curword])
        curword++
        if (curword == 10) {
            setTenWords()
        }
    }
}