package com.bam.achilifeapp.data.auth.models

data class LoggedUser(
    val id: String = "",
    val displayName: String = "",
    val role: Roles = Roles.ANONYMOUS
)


enum class Roles(r: String) {
    ANONYMOUS ("anonymous"),
    ADMIN ("admin"),
    USER ("user")
}

