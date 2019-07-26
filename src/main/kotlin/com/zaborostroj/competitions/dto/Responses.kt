package com.zaborostroj.competitions.dto

data class UserDto(
    val id: Long,
    val login: String,
    val name: String,
    val role: String,
    val email: String
)