package com.example.tic_tac_toe

import android.content.pm.ActivityInfo
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.view.marginTop
import com.example.tic_tac_toe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bn: ActivityMainBinding
    private var gameStarted: Boolean = false
    private var turn = 0; private val plays = arrayOf("O","X", "Player 2's Turn", "Player 1's Turn")
    private var check: Array<String> = Array(9) {""}
    private var P1SCORE = 0; private var P2SCORE = 0
    private var toast: Toast? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        bn = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bn.root)
        bn.btnStart.setOnClickListener {
            bn.btnStart.visibility = View.INVISIBLE
            gameStarted = true
            bn.apply {
                btnTouch1.text = ""
                btnTouch2.text = ""
                btnTouch3.text = ""
                btnTouch4.text = ""
                btnTouch5.text = ""
                btnTouch6.text = ""
                btnTouch7.text = ""
                btnTouch8.text = ""
                btnTouch9.text = ""
            }
        }
        bn.btnTouch1.setOnClickListener { setAndCheck(bn.btnTouch1, 1) }
        bn.btnTouch2.setOnClickListener { setAndCheck(bn.btnTouch2, 2) }
        bn.btnTouch3.setOnClickListener { setAndCheck(bn.btnTouch3, 3) }
        bn.btnTouch4.setOnClickListener { setAndCheck(bn.btnTouch4, 4) }
        bn.btnTouch5.setOnClickListener { setAndCheck(bn.btnTouch5, 5) }
        bn.btnTouch6.setOnClickListener { setAndCheck(bn.btnTouch6, 6) }
        bn.btnTouch7.setOnClickListener { setAndCheck(bn.btnTouch7, 7) }
        bn.btnTouch8.setOnClickListener { setAndCheck(bn.btnTouch8, 8) }
        bn.btnTouch9.setOnClickListener { setAndCheck(bn.btnTouch9, 9) }
    }
    private fun setAndCheck(btn: Button, pos: Int): Unit {
        if (gameStarted) {
            if (btn.text.toString() == "") {
                check[pos-1] = plays[turn%2]
                btn.text = plays[turn++%2]
                checkWin()
            } else {
                if (toast != null) toast!!.cancel()
                toast = Toast.makeText(applicationContext, "You can't place here", Toast.LENGTH_SHORT)
                toast!!.show()
            }
        } else {
            if (toast != null) toast!!.cancel()
            toast = Toast.makeText(applicationContext, "Please start the game by clicking start", Toast.LENGTH_SHORT)
            toast!!.show()
        }
    }
    private fun checkWin() {
        for (i in 0 until 3) for (j in 0 until 2) if (check[0 + (3 * i)] == plays[j] && check[1 + (3 * i)] == plays[j] && check[2 + (3 * i)] == plays[j]) finalizeWin(j)
        for (i in 0 until 3) for (j in 0 until 2) if (check[0 + i] == plays[j] && check[3 + i] == plays[j] && check[6 + i] == plays[j]) finalizeWin(j)
        for (i in 0 until 2) if (check[0] == plays[i] && check[4] == plays[i] && check[8] == plays[i]) finalizeWin(i)
        for (i in 0 until 2) if (check[2] == plays[i] && check[4] == plays[i] && check[6] == plays[i]) finalizeWin(i)
        if (turn == 9) finalizeWin(-1)
    }
    private fun finalizeWin(winner: Int) {
        if (winner == 0) bn.tvP1.text = "P1: ${++P1SCORE}" else if (winner == 1) bn.tvP2.text = "P2: ${++P2SCORE}"
        turn = 0
        check = Array(9) {""}
        bn.btnStart.visibility = View.VISIBLE
        gameStarted = false
    }
}
