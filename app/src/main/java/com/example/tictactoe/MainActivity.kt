package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tictactoe.API.Data.Game
import com.example.tictactoe.API.Data.GameState
import com.example.tictactoe.API.GameService
import com.example.tictactoe.API.GameServiceCallback
import com.example.tictactoe.Dialogs.CreateGameDialog
import com.example.tictactoe.Dialogs.GameDialogListener
import com.example.tictactoe.Dialogs.JoinGameDialog
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), GameDialogListener {
    val TAG:String = "MainActivity"


    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createGameButton.setOnClickListener {
            createNewGame()
        }
        binding.joinGameButton.setOnClickListener {
            joinGame()
        }
    }




    private fun createNewGame(){
        val dlg = CreateGameDialog()
        dlg.show(supportFragmentManager,"CreateGameDialog")
    }

    private fun joinGame(){
        val dialog = JoinGameDialog()
        dialog.show(supportFragmentManager, "JoinGameDialog")
    }


    override fun onDialogCreateGame(player: String) {
         GameManager.createGame(player, this)
    }

    override fun onDialogJoinGame(player: String, gameId: String) {
        GameManager.joinGame(player,gameId, this)
    }
}