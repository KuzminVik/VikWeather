package ru.geekbrains.myweather.presentation.search

import android.text.Editable
import android.text.TextWatcher

class ValidateInputText : TextWatcher {

    internal var isValid = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

    override fun afterTextChanged(editableText: Editable?) {
        isValid = validateNameCity(editableText)
    }

    companion object{
        fun validateNameCity(s: CharSequence?): Boolean{
            if (s.isNullOrEmpty() || s == " ") return false
            else{
                var count = 0
                for (i in s.indices) {
                    if (s[i] == ' ' && count == 0) {
                        count++
                    } else if (s[i] != ' ' && count == 1) {
                        count = 0
                    } else if (s[i] == ' ' && count != 0) {
                        return false
                    }
                }
                return true
            }
        }
    }
}