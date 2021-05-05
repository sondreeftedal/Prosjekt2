package com.example.tictactoe

import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.tictactoe.API.Data.Game
import com.example.tictactoe.API.Data.GameState
import com.example.tictactoe.API.GameService
import kotlinx.android.synthetic.main.activity_game.view.*
import kotlin.math.absoluteValue

object GameManager {

    var gameState:GameState? = null


    val StartingGameState:GameState = mutableListOf(mutableListOf(), mutableListOf(),
        mutableListOf()
    )

    fun createGame(player:String,context: Context) {
        var Game:Game
        GameService.createGame(player,StartingGameState) { game: Game?, err: Int? ->
            if(err != null){
                Log.e("Error", "$err")
            } else {
                val intent = Intent(context, GameActivity::class.java)
                intent.putExtra("Player", game!!.players.get(0))
                intent.putExtra("gameId", game.gameId)
                Game = game
                context.startActivity(intent)
            }
        }

    }

    fun buttonPressed(buttonposition:Int, state: GameState, buttonPressed:Button, gameid: String){
        gameState = state
        buttonPressed.setText("X")
        var index = buttonposition
        if(buttonposition <= 2){
            val row = gameState!!.get(0)
            row[index] = 1
            gameState!![0].set(index,1)
        }
        if(buttonposition in 3..5){
            val row = gameState!!.get(1)
            index -= 3
            row[index] = 1
            gameState!![1].set(index,1)
        }
        if(buttonposition > 5){
            val row = gameState!!.get(2)
            index -= 6
            row[index]= 1
            gameState!![1].set(index,1)
        }
        updateGame(gameid, gameState!!)
        buttonPressed.isEnabled = false
        checkWinner(state)
    }



    fun checkWinner(state: GameState){
        val gamestate = state
        val row1=gamestate.get(0)
        val row2 = gamestate.get(1)
        val row3= gamestate.get(2)
        var isWinner = false

       if(row1.get(0) == row1.get(1) && row1.get(0) == row1.get(2) && row1.get(0) != 0){
            Log.i("WINNERWINNER", "WOHOOO")
            isWinner = true
       }
        if(row2.get(0) == row2.get(1) && row2.get(0) == row2.get(2) && row2.get(0) != 0){
            Log.i("WINNERWINNER", "WOHOOO")
            isWinner = true
        }
        if(row3.get(0) == row3.get(1) && row3.get(0) == row3.get(2) && row3.get(0) != 0){
            Log.i("WINNERWINNER", "WOHOOO")
            isWinner = true
        }
        if(row1.get(0) == row2.get(0) && row1.get(0) == row3.get(0) && row1.get(0)!= 0){
            Log.i("WINNERWINNER", "WOHOOO")
            isWinner = true
        }
        if(row1.get(1) == row2.get(1) && row1.get(1) == row3.get(1) && row1.get(1)!= 0){
            Log.i("WINNERWINNER", "WOHOOO")
            isWinner = true
        }
        if(row1.get(2) == row2.get(2) && row1.get(2) == row3.get(2)&& row1.get(2)!= 0){
            Log.i("WINNERWINNER", "WOHOOO")
            isWinner = true
        }
        if(row1.get(0) == row2.get(1) && row1.get(0) == row3.get(2)&& row1.get(0)!= 0){
            Log.i("WINNERWINNER", "WOHOOO")
            isWinner = true
        }
        if(row1.get(2) == row2.get(1) && row1.get(2) == row3.get(0) && row1.get(2) != 0){
            Log.i("WINNERWINNER", "WOHOOO")
            isWinner = true
        }
        if(isWinner == true){
            announceWinner()
        }

    }
    fun announceWinner(){
        Log.i("Winner", "Vi har en vinner")
    }


    fun updateGame(gameid:String,newState:GameState){
        GameService.updateGame(gameid,newState,{game: Game?, errorCode: Int? ->
            Log.i("state", game!!.state.toString())
        })
    }

    fun joinGame(playerId:String,gameid: String, context: Context){
        GameService.joinGame(playerId, gameid,{game: Game?, errorCode: Int? ->
            Log.i("game", game.toString())
            val intent = Intent(context, GameActivity::class.java)
            intent.putExtra("Player", playerId)
            intent.putExtra("gameId", gameid)
            context.startActivity(intent)
        })
    }

}