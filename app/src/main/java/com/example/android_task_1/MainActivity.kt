package com.example.android_task_1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.android_task_1.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val MAX_NUMBER:Int = 6;
    private val MIN_NUMBER:Int = 0;
    private enum class DiceExpectation {
        LOWER, SAME, HIGHER
    }
    private var diceNumber:Int = 0;

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
        val hasWon = hasWonDiceRoll(this.diceNumber, dice.toInt(), diceExpectation);
        this.diceNumber = dice

        updateUI(hasWon)
    }

    private fun updateUI(hasWon: Boolean)
    {
        updateDiceImage()
        displaySuccessOrFailed(hasWon)
    }

    private fun updateDiceImage() {

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
        binding.diceTextView.text = getString(R.string.you_rolled, this.diceNumber)
    }

    private fun displaySuccessOrFailed(hasWon: Boolean) {
        if(hasWon)
        {
            Toast.makeText(this, R.string.you_won, Toast.LENGTH_LONG).show()
            return
        }
        Toast.makeText(this, R.string.you_lost, Toast.LENGTH_LONG).show()

    }

}