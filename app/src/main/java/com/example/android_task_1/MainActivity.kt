package com.example.android_task_1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.android_task_1.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val MAX_NUMBER:Int = 6
    private val MIN_NUMBER:Int = 0
    private enum class DiceExpectation {
        LOWER, SAME, HIGHER
    }
    private var diceNumber:Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Sets the activity layout

        setButtonListeners();
    }

    private fun setButtonListeners() {
        binding.rollHighButton.setOnClickListener {
            rollDice(DiceExpectation.HIGHER)
        }
        binding.rollLowButton.setOnClickListener {
            rollDice(DiceExpectation.LOWER)
        }
        binding.rollSameButton.setOnClickListener {
            rollDice(DiceExpectation.SAME)
        }
    }

    private fun rollDice(diceExpectation: DiceExpectation) {
        updateDiceNumber()
        val dice = (1..6).random()
        val hasWon = hasWonDiceRoll(this.diceNumber, dice, diceExpectation);
        this.diceNumber = dice

        updateUI(hasWon)
    }

    private fun updateUI(hasWon: Boolean)
    {
        updateDiceImage()
        displaySuccessOrFailed(hasWon)
    }

    private fun updateDiceImage() {
        var drawable = R.drawable.dice1

        when (this.diceNumber) {
            1 -> drawable = R.drawable.dice1
            2 -> drawable = R.drawable.dice2
            3 -> drawable = R.drawable.dice3
            4 -> drawable = R.drawable.dice4
            5 -> drawable = R.drawable.dice5
            6 -> drawable = R.drawable.dice6
        }

        binding.diceImageView.setImageDrawable(
            ContextCompat.getDrawable(
            applicationContext, // Context
                drawable// Drawable
        ));
    }

    private fun hasWonDiceRoll(previousDice: Int, currentDice: Int, diceExpectation: DiceExpectation):Boolean {
        if(diceExpectation == DiceExpectation.HIGHER)
        {
            return (previousDice < currentDice)
        } else if(diceExpectation == DiceExpectation.LOWER) {
            return (previousDice > currentDice)
        }

        return (previousDice == currentDice)
    }

    private fun updateDiceNumber() {
        binding.diceTextView.text = getString(R.string.last_throw, this.diceNumber)
    }

    private fun displaySuccessOrFailed(hasWon: Boolean) {
        if(hasWon)
        {
            Toast.makeText(this, R.string.you_won, Toast.LENGTH_SHORT).show()
            return
        }
        Toast.makeText(this, R.string.you_lost, Toast.LENGTH_SHORT).show()

    }

}