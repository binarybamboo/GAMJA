package com.example.gamja.view.dialog

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.example.gamja.R

class AddDiaryDialog(context: Context) {
    private val dialog=Dialog(context)
    interface OnClickListener{
        fun onClicked(diaryName: String)
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
        val diaryName=dialog.findViewById<EditText>(R.id.add_diary_title)
        btnAdd.setOnClickListener{
            onClickListener.onClicked(diaryName.text.toString())
            dialog.dismiss()
        }
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
    }
}