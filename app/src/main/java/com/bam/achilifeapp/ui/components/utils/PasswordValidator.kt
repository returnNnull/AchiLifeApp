package com.bam.achilifeapp.ui.components.utils

class PasswordValidator(length: Int = -1) : TextValidator(length) {

    override fun check(text: String): Report {

        return super.check(text)
    }
}