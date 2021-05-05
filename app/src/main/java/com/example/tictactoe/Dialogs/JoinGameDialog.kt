package com.example.tictactoe.Dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.tictactoe.databinding.ActivityMainBinding.inflate
import com.example.tictactoe.databinding.DialogCreateGameBinding
import com.example.tictactoe.databinding.DialogJoinGameBinding
import java.lang.ClassCastException
import java.lang.IllegalStateException

class JoinGameDialog() : DialogFragment() {

    internal lateinit var listener:GameDialogListener


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder: AlertDialog.Builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val binding = DialogJoinGameBinding.inflate(inflater)
            builder.apply {
                setTitle("Join game")
                setPositiveButton("Join"){dialog, which ->
                    if(binding.toString() != ""){
                        listener.onDialogJoinGame(binding.enterUserName.text.toString(),binding.gameId.text.toString())

                    }
                }
                setNegativeButton("Cancel"){dialog, which ->
                    dialog.cancel()
                }
                setView(binding.root)
            }
            builder.create()
        }?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as GameDialogListener
        }catch (e: ClassCastException){
            throw ClassCastException(("$context must implement gameDialogListener"))
        }
    }
}