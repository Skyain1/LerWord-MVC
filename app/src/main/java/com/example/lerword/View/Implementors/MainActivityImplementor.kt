package com.example.lerword.View.Implementors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.lerword.Controller.MainController
import com.example.lerword.Model.Database.UserDao
import com.example.lerword.Model.Database.UserDatabase
import com.example.lerword.Model.Database.WordsRepository
import com.example.lerword.Model.Word
import com.example.lerword.R
import com.example.lerword.View.Adapters.CardStackAdapter
import com.example.lerword.View.Interfaces.CardItemView
import com.example.lerword.View.Interfaces.MainActivityView
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.Duration
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting

/*
 * Created by Skyain1 on 25.06.2023.
 */

class MainActivityImplementor(val context: Context, viewGroup: ViewGroup?) : MainActivityView,
    CardStackListener, CardItemView.ButtonClickListener {

    private var rootView: View
    private var controller: MainController
    private  var dao: UserDao
    private  var repository: WordsRepository
    private lateinit var cardStackView: CardStackView
    private lateinit var CSLmanager: CardStackLayoutManager

    private lateinit var stats :TextView
    private lateinit var Continue : ConstraintLayout
    private lateinit var cont : ImageButton

    init {
        rootView= LayoutInflater.from(context).inflate(R.layout.activity_main,viewGroup)
        dao = UserDatabase.getDatabase(context).getUserDao()
        repository = WordsRepository()
        controller = MainController(repository,this)
    }

    override fun initViews() {
        cardStackView = rootView.findViewById(R.id.card_stack_view)
        CSLmanager = CardStackLayoutManager(context, this)
        CSLmanager.setCanScrollHorizontal(true)
        CSLmanager.setCanScrollVertical(false)
        cardStackView.layoutManager = CSLmanager
        stats = rootView.findViewById(R.id.stats)
        Continue =  rootView.findViewById(R.id.Continue)
        cont = rootView.findViewById(R.id.cont)
    }

    override fun onCardSwiped(direction: Direction?) {
        controller.cardSwipe(direction)
    }

    override fun bindDatatoView() {
       controller.setTenWords()
    }

    override fun getData(words: ArrayList<Word>) {
        cardStackView.adapter = CardStackAdapter( context,words, this)
        cardStackView.adapter?.notifyDataSetChanged()
    }

    override fun setProgress(knowWords:Int) {
        stats.text = "Заучено $knowWords новых слов"
        if (knowWords in 1..10) {
            val resourceId =
                context.resources.getIdentifier("strip$knowWords", "id", context.packageName)
            if (resourceId != 0) {
                val imageView = rootView.findViewById<ImageView>(resourceId)
                imageView.setImageResource(R.drawable.progress_active)
            }
        }
    }

    override fun setRestart() {
        cardStackView.visibility = View.INVISIBLE
        Continue.visibility = View.VISIBLE
        cont.setOnClickListener {
            cardStackView.visibility = View.VISIBLE
            Continue.visibility = View.GONE
            for (i in 1..10) {
                val resourceId =
                    context.resources.getIdentifier("strip$i", "id", context.packageName)
                if (resourceId != 0) {
                    val imageView = rootView.findViewById<ImageView>(resourceId)
                    imageView.setImageResource(R.drawable.progress_unactive)
                }
            }
            stats.text = "Заучено 0 новых слов"
        }

    }

    override fun showErrorToast(error: String) {
        Toast.makeText(rootView.context,error, Toast.LENGTH_SHORT).show()
    }

    override fun getView(): View {
        return rootView
    }

    override fun onButtonRightClick() {
        val setting = SwipeAnimationSetting.Builder()
            .setDirection(Direction.Right)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()
        CSLmanager.setSwipeAnimationSetting(setting)
        cardStackView.swipe()
    }

    override fun onButtonLeftClick() {
        val setting = SwipeAnimationSetting.Builder()
            .setDirection(Direction.Left)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()
        CSLmanager.setSwipeAnimationSetting(setting)
        cardStackView.swipe()
    }

//*********************************************
    override fun onCardRewound() {}
    override fun onCardCanceled() {}
    override fun onCardAppeared(view: View?, position: Int) {}
    override fun onCardDisappeared(view: View?, position: Int) {}
    override fun onCardDragging(direction: Direction?, ratio: Float) {}
}