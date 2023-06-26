package com.example.lerword.View.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lerword.Model.Word
import com.example.lerword.R
import com.example.lerword.View.Implementors.WordCardViewImplementor
import com.example.lerword.View.Interfaces.CardItemView

/*
 * Created by Skyain1 on 26.06.2023.
 */

class CardStackAdapter(
    private val context: Context,
    private val words: List<Word>,
    private val itemClickListener: CardItemView.ButtonClickListener,
) : RecyclerView.Adapter<CardStackAdapter.WordViewHolder>(), CardItemView.ButtonClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val wordListItemMVC = WordCardViewImplementor(layoutInflater, parent)
        wordListItemMVC.initViews()
        wordListItemMVC.setCardButtonClickListener(this)
        return WordViewHolder(wordListItemMVC)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = words[position]
        holder.listItemMVC.bindDataToView(word)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.card_viewholder
    }

    override fun getItemCount(): Int {
        return words.size
    }

    inner class WordViewHolder(var listItemMVC: WordCardViewImplementor) :
        RecyclerView.ViewHolder(listItemMVC.getView()) {
    }

    override fun onButtonRightClick() {
        itemClickListener.onButtonRightClick()
    }

    override fun onButtonLeftClick() {
        itemClickListener.onButtonLeftClick()
    }
}








