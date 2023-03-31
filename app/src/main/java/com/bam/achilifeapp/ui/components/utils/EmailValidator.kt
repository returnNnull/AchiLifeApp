package com.bam.achilifeapp.ui.components.utils

class EmailValidator(length: Int = -1) : TextValidator(length) {

    private val emailRegex = "^[A-Za-z\\d+_.-]+@[A-Za-z\\d.-]+\$"

    override fun check(text: String): Report {
        if (!text.matches(emailRegex.toRegex())) {
            return Report(false, "Введите email")
        }
        return super.check(text)

    }
}