package com.example.gamja.utils

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart

@ExperimentalCoroutinesApi
fun EditText.textChangesToFlow(): Flow<CharSequence?> {

    //flow 콜백받기
    return callbackFlow<CharSequence?> {
        val listener=object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Unit
            }
            override fun afterTextChanged(s: Editable?) {
                Unit
            }
            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("TAG", "onTextChanged $text")
                offer(text)

            }
        }
        addTextChangedListener(listener)
        //콜백사라질때 발동
        awaitClose {
            Log.d("TAG", "textChangesToFlow: 콜백삭제")
            removeTextChangedListener(listener)
        }
    }.onStart {
        Log.d("TAG", "textChangesToFlow: 발동")
        emit(text)
    }
}
//문자열이 제이슨 형태인지, 제이슨 배열 형태인지
fun String?.isJsonObject(): Boolean=this?.startsWith("{")==true && this.endsWith("}")
fun String?.isJsonArray():Boolean=this?.startsWith("[")==true && this.endsWith("]")

enum class RESPONSE_STATE{
    OK,FAIL
}