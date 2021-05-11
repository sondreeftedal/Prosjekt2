package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tictactoe.databinding.ActivityWinnerBinding



class WinnerActivity : AppCompatActivity() {
    lateinit var binding: ActivityWinnerBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val winner = intent.getStringExtra("winner")

        if(winner != "Draw"){
            binding.winnerText.text = "$winner won the game!"
        }else if(winner == "Draw"){
            binding.winnerText.text = "Draw"
        }

        binding.mainMenuButton.setOnClickListener {
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
}