package com.example.lerword.View.Implementors
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.lerword.Model.Word
import com.example.lerword.R
import com.example.lerword.View.Interfaces.CardItemView

/*
 * Created by Skyain1 on 26.06.2023.
 */

class WordCardViewImplementor (layoutInflater: LayoutInflater,parent : ViewGroup) : CardItemView<Word> {

    private var rootView: View
    private lateinit var textView: TextView
    private lateinit var  theme: TextView
    private lateinit var perevod: TextView
    private lateinit var newword: TextView
    private lateinit var  indicator: ImageView
    private lateinit var leftbutton: ConstraintLayout
    private lateinit var rightbutton: ConstraintLayout
    private lateinit var openbutton: ConstraintLayout
    lateinit var translate: ConstraintLayout
    lateinit var op: ImageButton
    lateinit var word: Word
    lateinit var listener: CardItemView.ButtonClickListener

    init {
        rootView = layoutInflater.inflate(R.layout.card_viewholder, parent, false)
    }

    override fun initViews() {
        textView = rootView.findViewById(R.id.textView)
        theme = rootView.findViewById(R.id.textView3)
        perevod = rootView.findViewById(R.id.perevod)
        newword = rootView.findViewById(R.id.textView2)
        indicator = rootView.findViewById(R.id.imageView)

        leftbutton = rootView.findViewById(R.id.leftbutton)
        rightbutton = rootView.findViewById(R.id.rightbutton)
        openbutton = rootView.findViewById(R.id.openbutton)
        translate = rootView.findViewById(R.id.translate)
        op = rootView.findViewById(R.id.op)
    }

    override fun setCardButtonClickListener(listener: CardItemView.ButtonClickListener) {
        this.listener = listener
    }

    override fun getView(): View {
        return rootView
    }

    override fun bindDataToView(unit: Word) {
        this.word = unit

        textView.text = word.Title
        theme.text = word.Theme
        perevod.text = word.Translate
        translate.visibility = View.INVISIBLE
        openbutton.visibility = View.VISIBLE
        leftbutton.setBackgroundColor(Color.parseColor("#00000000"))
        rightbutton.setBackgroundColor(Color.parseColor("#00000000"))
        if (word.CheckCount > 0) {
            if (word.know) {
                indicator.setImageResource(R.drawable.icon_know_word)
                newword.text = "Повтор заученного слова"
            } else {
                indicator.setImageResource(R.drawable.icon_exists_word)
                newword.text = "${word.CheckCount}й повтор слова"
            }
        } else {
            indicator.setImageResource(R.drawable.new_word)
            newword.text = "Новое слово"
        }

        leftbutton.setOnClickListener {
            leftbutton.setBackgroundColor(Color.parseColor("#EEDEA4"))
            listener.onButtonLeftClick()

        }

        rightbutton.setOnClickListener {
            rightbutton.setBackgroundColor(Color.parseColor("#A4A5EE"))
            listener.onButtonRightClick()
        }

        op.setOnClickListener {
            openbutton.visibility = View.INVISIBLE
            translate.alpha = 0f
            translate.visibility = View.VISIBLE
            translate.animate()
                .alpha(1f)
                .setDuration(300)
                .start()
        }
    }
}