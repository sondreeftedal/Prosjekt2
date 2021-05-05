package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.example.tictactoe.API.Data.Game
import com.example.tictactoe.API.Data.GameState
import com.example.tictactoe.API.GameService
import com.example.tictactoe.databinding.ActivityGameBinding
import kotlinx.android.synthetic.main.activity_game.view.*


private lateinit var binding: ActivityGameBinding
var startState:GameState = mutableListOf(mutableListOf(0,0,0), mutableListOf(0,0,0), mutableListOf(0,0,0))
lateinit var Game: Game


class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val player = intent.getStringExtra("Player")
        val gameId = intent.getStringExtra("gameId")
        binding.Playername.setText(player)
        binding.roomCode.setText(gameId)

        binding.button1.setOnClickListener {
            GameManager.buttonPressed(0, startState, it.button1, gameId.toString())
        }
        binding.button2.setOnClickListener {
            GameManager.buttonPressed(1, startState, it.button2, gameId.toString())
        }
        binding.button3.setOnClickListener {
            GameManager.buttonPressed(2, startState, it.button3, gameId.toString())
        }
        binding.button4.setOnClickListener {
            GameManager.buttonPressed(3, startState, it.button4, gameId.toString())
        }
        binding.button5.setOnClickListener {
            GameManager.buttonPressed(4, startState, it.button5, gameId.toString())
        }
        binding.button6.setOnClickListener {
            GameManager.buttonPressed(5, startState, it.button6, gameId.toString())
        }
        binding.button7.setOnClickListener {
            GameManager.buttonPressed(6, startState, it.button7, gameId.toString())
        }
        binding.button8.setOnClickListener {
            GameManager.buttonPressed(7, startState, it.button8, gameId.toString())
        }
        binding.button9.setOnClickListener {
            GameManager.buttonPressed(8, startState, it.button9, gameId.toString())
        }

        val timer = object :CountDownTimer(5000,1000){
            override fun onTick(millisUntilFinished: Long) {
                print("$millisUntilFinished")
            }

            override fun onFinish() {
                pollGame(intent.getStringExtra("gameId").toString())
                start()
            }
        }
        timer.start()




    }


fun pollGame(gameid:String){
    GameService.pollGame(gameid,{game: Game?, errorCode: Int? ->

    })

}
}