package com.zaborostroj.competitions.dto

import com.zaborostroj.competitions.entities.Role
import com.zaborostroj.competitions.entities.User

fun User.toDto() = UserResponse(
    login = login,
    firstName = firstName,
    lastName = lastName,
    email = email,
    enabled = enabled,
    roles = roles?.map(Role::name)
)
