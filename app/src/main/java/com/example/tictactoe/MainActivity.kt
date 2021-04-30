package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tictactoe.API.Data.Game
import com.example.tictactoe.API.GameService
import com.example.tictactoe.API.GameServiceCallback
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val TAG:String = "MainActivity"


    lateinit var binding: ActivityMainBinding
    var startState:List<List<Int>> = listOf(listOf(0,0,0), listOf(0,0,0), listOf(0,0,0))
    var testState:List<List<Int>> = listOf(listOf(1,0,1), listOf(0,1,0), listOf(1,1,1))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GameService.pollGame("fzjteg", {state: Game?, errorCode: Int? ->

        })
    }




    private fun createNewGame(){
        //val dlg = CreateGameDialog()
        //dlg.show(supportFragmentManager,"CreateGameDialogFragment")


    }

    private fun joinGame(){
        //GameService.createGame("Test",startState, Unit)
    }
/*
    override fun onDialogCreateGame(player: String) {
        Log.d(TAG,player)
    }

    override fun onDialogJoinGame(player: String, gameId: String) {
        Log.d(TAG, "$player $gameId")
    }*/

    fun callback(game: Game,errorCode:Int?){
        TODO()
    }
}