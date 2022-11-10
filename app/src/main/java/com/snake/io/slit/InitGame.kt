package com.snake.io.slit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.orhanobut.hawk.Hawk
import com.snake.io.slit.databinding.ActivityInitGameBinding

class InitGame : AppCompatActivity() {
    lateinit var bind: ActivityInitGameBinding
    private lateinit var el1: ImageView
    lateinit var el2: ImageView
    lateinit var el3: ImageView
    lateinit var el4: ImageView
    lateinit var elAppear: ImageView
    lateinit var text: TextView
    lateinit var textVic: TextView
    var intUsed: Int = Hawk.get("INT_USED", 1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityInitGameBinding.inflate(layoutInflater)
        setContentView(bind.root)
        el1 = bind.card1
        el2 = bind.card2
        el3 = bind.card3
        el4 = bind.card4
        elAppear = bind.cardAwake
        text = bind.ruleTextSet
        textVic = bind.vicScore

        textVic.text = "Your Victory Count:$intUsed"

        el1.setOnClickListener {
            val result = (1..4).random()
            if (result == 3){
                elAppear.setImageResource(R.drawable.elementtwo)
                Snackbar.make(
                    bind.root, "Hurray! You have won!",
                    Snackbar.LENGTH_LONG
                ).show()
                text.text = "Wow! You have Won! Amazing"
                intUsed++
                Hawk.put("INT_USED", intUsed)
            } else{
                text.text = "You did not guess the element. Try Again!"
            }
            el1.isClickable = false
            el2.isClickable = false
            el3.isClickable = false
            el4.isClickable = false
        }

        el2.setOnClickListener {
            val result = (1..4).random()
            if (result == 2){
                elAppear.setImageResource(R.drawable.elementfour)
                Snackbar.make(
                    bind.root, "Hurray! You have won!",
                    Snackbar.LENGTH_LONG
                ).show()
                text.text = "Wow! You have Won! Amazing"
                intUsed++
                Hawk.put("INT_USED", intUsed)
            }else{
                text.text = "You did not guess the element. Try Again!"
            }
            el1.isClickable = false
            el3.isClickable = false
            el3.isClickable = false
            el4.isClickable = false
        }

        el3.setOnClickListener {
            val result = (1..4).random()
            if (result == 1){
                elAppear.setImageResource(R.drawable.elementthree)
                Snackbar.make(
                    bind.root, "Hurray! You have won!",
                    Snackbar.LENGTH_LONG
                ).show()
                text.text = "Wow! You have Won! Amazing"
                intUsed++
                Hawk.put("INT_USED", intUsed)
            }else{
                text.text = "You did not guess the element. Try Again!"
            }
            el1.isClickable = false
            el2.isClickable = false
            el3.isClickable = false
            el4.isClickable = false
        }
        el4.setOnClickListener {
            val result = (1..4).random()
            if (result == 4){
                elAppear.setImageResource(R.drawable.elementone)
                Snackbar.make(
                    bind.root, "Hurray! You have won!",
                    Snackbar.LENGTH_LONG
                ).show()
                text.text = "Wow! You have Won! Amazing"
                intUsed++
                Hawk.put("INT_USED", intUsed)
            }else{
                text.text = "You did not guess the element. Try Again!"
            }
            el1.isClickable = false
            el2.isClickable = false
            el3.isClickable = false
            el4.isClickable = false
        }
    }

    fun retry(view: View) {
        this.recreate()
    }

    fun moveToAch(view: View) {
        Intent(applicationContext, HallOfFame::class.java).also { startActivity(it) }

    }
}