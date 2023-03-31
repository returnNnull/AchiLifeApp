package com.bam.achilifeapp.ui.components.utils

open class TextValidator(private val length: Int = -1) {

   open fun check(text: String): Report {
       if (text.isBlank()) {
           return Report(false, "Поле не может быть пустым или содержать пробелы")
       }

       if (text.length < length) {
           return Report(false, "Минимальная длина $length")
       }

       return Report(true)
   }


    data class Report(val isValid: Boolean, val error: String = "" )
}