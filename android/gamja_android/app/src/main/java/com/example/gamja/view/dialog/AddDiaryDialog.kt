package com.example.gamja.view.dialog

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import com.example.gamja.R

class AddDiaryDialog(context: Context) {
    private val dialog=Dialog(context)
    interface OnClickListener{
        fun onClicked()
    }
    private lateinit var onClickListener:OnClickListener
    fun setOnClickListener(listener: OnClickListener){
        onClickListener=listener
    }
    fun myDig(){
        dialog.setContentView(R.layout.add_diary_dialog)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()

        val btnCancel=dialog.findViewById<Button>(R.id.cancel_btn)
        val btnAdd=dialog.findViewById<Button>(R.id.add_btn)
        btnAdd.setOnClickListener{
            onClickListener.onClicked()
            dialog.dismiss()
        }
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
    }
}