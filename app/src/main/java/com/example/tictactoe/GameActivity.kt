package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import com.example.tictactoe.API.Data.Game
import com.example.tictactoe.API.Data.GameBoard
import com.example.tictactoe.API.Data.GameState
import com.example.tictactoe.API.GameService
import com.example.tictactoe.databinding.ActivityGameBinding



private lateinit var binding: ActivityGameBinding
var state:GameState = mutableListOf(mutableListOf("","",""), mutableListOf("","",""), mutableListOf("","",""))
lateinit var Game: Game
lateinit var gameBoard: GameBoard
var player1Turn:Boolean = true
var numberOfX = 0
var numberOfO = 0
lateinit var player: String
class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        player = intent.getStringExtra("Player")!!
        val gameId = intent.getStringExtra("gameId")
        val player1 = intent.getBooleanExtra("isPlayer1", false)



        Game = Game(mutableListOf(),gameId.toString(), state)
        Game.players.add(player.toString())
        Log.i("Players", player.toString())

        binding.Player1name.setText("You: $player")
        binding.roomCode.setText("Room code: $gameId")
        gameBoard = GameBoard(mutableListOf(mutableListOf(binding.button1, binding.button2,binding.button3), mutableListOf(binding.button4,
            binding.button5, binding.button6),
            mutableListOf(binding.button7,binding.button8,binding.button9)))



        for(i in 0..2){
            for(j in 0..2){
                val button = gameBoard.buttonList[i][j]
                gameBoard.buttonList[i][j].setOnClickListener {
                        if(numberOfX <= numberOfO && player1) {
                        GameManager.buttonPressed(i, j, state, button, gameId.toString(), player.toString(),
                            player1)
                            player1Turn = false
                            Log.i("PlayerTurn", player1Turn.toString())

                    }
                    else if(numberOfO < numberOfX && player1 == false){
                            GameManager.buttonPressed(i, j, state, button, gameId.toString(),
                                player.toString(),
                                player1)
                            player1Turn = true
                    }else{
                            Toast.makeText(this.applicationContext,"Wait for other player to finish turn", Toast.LENGTH_SHORT).show()
                        }
                }


            }
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

    GameService.pollGame(gameid) { game: Game?, errorCode: Int? ->
        if (errorCode == null) {
            state = game!!.state
            Game = game
            numberOfX = 0
            numberOfO = 0
            var totalTurns = 0
            for (i in 0..2) {
                numberOfX += gameBoard.buttonList[i].count { e -> e.text == "X" }
                numberOfO += gameBoard.buttonList[i].count { e -> e.text == "O" }
                for (j in 0..2) {
                    gameBoard.buttonList[i][j].text = state[i][j]
                    if (gameBoard.buttonList[i][j].text != "") {
                        gameBoard.buttonList[i][j].isEnabled = false
                    }
                }
            }
            if(game.players.count() == 2 && game.players[1] != player){
                binding.Player2name.text = "Opponent: ${game.players[1]}"
            }else if(game.players.count() == 2 && game.players[0] != player){
                binding.Player2name.text = "Opponent: ${game.players[0]}"
            }
            totalTurns = numberOfO + numberOfX
            GameManager.checkWinner(game,this, totalTurns)


        } else {
            Log.e("Error", "$errorCode")
        }
    }

}
}