package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var buttons: Array<Button>
    private lateinit var tvPlayerTurn: TextView
    private var playerX = true
    private var gameOver = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)

        buttons = arrayOf(button1, button2, button3, button4, button5, button6, button7, button8, button9)

        tvPlayerTurn = findViewById(R.id.textPlayerTurn)
        tvPlayerTurn.text = getString(R.string.player_x_turn)

        for (button in buttons) {
            button.setOnClickListener { onButtonClick(it) }
        }
        findViewById<Button>(R.id.buttonNewGame).setOnClickListener { onNewGameClick() }
    }

    private fun onButtonClick(view: View) {
        if (gameOver) return

        val button = view as Button
        if (button.text.isEmpty()) {
            button.text = if (playerX) "X" else "O"
            if (checkWin()) {
                tvPlayerTurn.text = if (playerX) getString(R.string.player_x_wins) else getString(R.string.player_o_wins)
                gameOver = true
            } else if (checkTie()) {
                tvPlayerTurn.text = getString(R.string.game_tie)
                gameOver = true
            } else {
                playerX = !playerX
                updateTurnText()
            }
        }
    }

    private fun onNewGameClick() {
        // Clear all buttons
        for (button in buttons) {
            button.text = ""
            button.isEnabled = true
        }
        playerX = true
        gameOver = false
        updateTurnText()
    }

    private fun updateTurnText() {
        tvPlayerTurn.text = if (playerX) getString(R.string.player_x_turn) else getString(R.string.player_o_turn)
    }

    private fun checkWin(): Boolean {
        val winningCombinations = arrayOf(
            arrayOf(0, 1, 2),
            arrayOf(3, 4, 5),
            arrayOf(6, 7, 8),
            arrayOf(0, 3, 6),
            arrayOf(1, 4, 7),
            arrayOf(2, 5, 8),
            arrayOf(0, 4, 8),
            arrayOf(2, 4, 6)
        )

        for (combo in winningCombinations) {
            if (buttons[combo[0]].text == buttons[combo[1]].text && buttons[combo[1]].text == buttons[combo[2]].text && buttons[combo[0]].text.isNotEmpty()) {
                return true
            }
        }
        return false
    }

    private fun checkTie(): Boolean {
        for (button in buttons) {
            if (button.text.isEmpty()) {
                return false
            }
        }
        return true
    }



}
