package com.example.tictactoe

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.contentValuesOf
import com.example.tictactoe.API.Data.Game
import com.example.tictactoe.API.Data.GameState
import com.example.tictactoe.API.GameService
import kotlinx.android.synthetic.main.activity_game.view.*
import kotlin.math.absoluteValue

object GameManager {

    var gameState:GameState? = null


    var StartingGameState:GameState = mutableListOf(mutableListOf("","",""), mutableListOf("","",""), mutableListOf("","",""))


    fun createGame(player:String,context: Context) {
        GameService.createGame(player,StartingGameState) { game: Game?, err: Int? ->
            if(err != null){
                Log.e("Error", "$err")
            } else {
                val intent = Intent(context, GameActivity::class.java)
                intent.putExtra("Player", player)
                intent.putExtra("gameId", game!!.gameId)
                intent.putExtra("isPlayer1", true)
                context.startActivity(intent)

            }
        }

    }


    fun buttonPressed(list:Int,postition:Int ,state: GameState, buttonPressed:Button, gameid: String, player: String, player1:Boolean){
        gameState = state
        if(player1){
            buttonPressed.setText("X")
        if(list == 0){
            val row = gameState!!.get(0)
            row[postition] = "X"
            gameState!![0].set(postition,"X")
        }
        else if(list == 1){
            val row = gameState!!.get(1)
            row[postition] = "X"
            gameState!![1].set(postition,"X")
        }
        else{
            val row = gameState!!.get(2)
            row[postition]= "X"
            gameState!![2].set(postition,"X")
        }
            updateGame(gameid, gameState!!)
            buttonPressed.isEnabled = false
        }
        else if(!player1){
            buttonPressed.setText("O")
            if(list == 0){
                val row = gameState!!.get(0)
                row[postition] = "O"
                gameState!![0].set(postition,"O")
            }
            else if(list == 1){
                val row = gameState!!.get(1)
                row[postition] = "O"
                gameState!![1].set(postition,"O")
            }
            else{
                val row = gameState!!.get(2)
                row[postition]= "O"
                gameState!![2].set(postition,"O")
            }

        }
        updateGame(gameid, gameState!!)
        buttonPressed.isEnabled = false
    }



    fun checkWinner(game:Game, context: Context, totalTurns: Int){
        val gamestate = game.state
        val row1=gamestate.get(0)
        val row2 = gamestate.get(1)
        val row3= gamestate.get(2)
        var Winner = ""


       if(row1.get(0) == row1.get(1) && row1.get(0) == row1.get(2) && row1.get(0) != ""){
           Winner = row1.get(1)

       }
        if(row2.get(0) == row2.get(1) && row2.get(0) == row2.get(2) && row2.get(0) != ""){
            Winner = row2.get(0)
        }
        if(row3.get(0) == row3.get(1) && row3.get(0) == row3.get(2) && row3.get(0) != ""){
            Winner = row3.get(0)
        }
        if(row1.get(0) == row2.get(0) && row1.get(0) == row3.get(0) && row1.get(0)!= ""){
            Winner = row1.get(0)
        }
        if(row1.get(1) == row2.get(1) && row1.get(1) == row3.get(1) && row1.get(1)!= ""){
            Winner = row1.get(1)
        }
        if(row1.get(2) == row2.get(2) && row1.get(2) == row3.get(2)&& row1.get(2)!= ""){
            Winner = row1.get(2)
        }
        if(row1.get(0) == row2.get(1) && row1.get(0) == row3.get(2)&& row1.get(0)!= ""){
            Winner = row1.get(0)
        }
        if(row1.get(2) == row2.get(1) && row1.get(2) == row3.get(0) && row1.get(2) != ""){
            Winner = row1.get(2)
        }
        if(Winner == "X"){
            val intent = Intent(context.applicationContext,WinnerActivity::class.java)
            intent.putExtra("winner", game.players[0])
            context.startActivity(intent)
        }else if(Winner == "O"){
            val intent = Intent(context.applicationContext,WinnerActivity::class.java)
            intent.putExtra("winner", game.players[1])
            context.startActivity(intent)
        }
        if(Winner == "" && totalTurns == 9){
            val intent = Intent(context.applicationContext, WinnerActivity::class.java)
            intent.putExtra("winner", "Draw")
            context.startActivity(intent)

        }

    }



    fun updateGame(gameid:String,newState:GameState){
        GameService.updateGame(gameid,newState,{game: Game?, errorCode: Int? ->
            Log.i("state", game!!.state.toString())
        })
    }

    fun joinGame(playerId:String,gameid: String, context: Context){
        GameService.joinGame(playerId, gameid) { game: Game?, errorCode: Int? ->
            val intent = Intent(context, GameActivity::class.java)
            intent.putExtra("Player", playerId)
            intent.putExtra("gameId", game!!.gameId)
            intent.putExtra("isPlayer1", false)
            context.startActivity(intent)
        }
    }

}