package com.example.tictactoe.API.Data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


typealias GameState = List<List<Int>>

@Parcelize
data class Game(val players:MutableList<String>, val gameID:String, val state:GameState):Parcelable