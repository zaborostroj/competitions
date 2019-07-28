package com.zaborostroj.competitions.entities

import com.zaborostroj.competitions.dto.UserDto

fun User.toDto(): UserDto {
    return UserDto(
        id = id,
        login = login,
        name = name,
        role = role,
        email = email
    )
}