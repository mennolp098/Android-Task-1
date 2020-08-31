package com.example.android_task_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_task_1.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val MAX_NUMBER:Int = 6;
    private val MIN_NUMBER:Int = 0;
    private var diceNumber:Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Sets the activity layout

        setButtonListeners();
    }

    private fun setButtonListeners() {
        binding.rollHighButton.setOnClickListener {
            rollDice(MIN_NUMBER, MAX_NUMBER)
        }
        binding.rollLowButton.setOnClickListener {
            rollDice(MIN_NUMBER, MAX_NUMBER)
        }
        binding.rollHighButton.setOnClickListener {
            rollDice(MIN_NUMBER, MAX_NUMBER)
        }
    }

    private fun rollDice(min:Int, max:Int) {
        val dice = (Math.random() * ((max - min) + 1)) + min
        this.diceNumber = dice.toInt()

        displayDiceNumber()
    }

    private fun displayDiceNumber() {
        binding.diceTextView.text = this.diceNumber.toString()
    }

}