package com.zaborostroj.competitions.services

import com.zaborostroj.competitions.dto.UserDto
import com.zaborostroj.competitions.entities.User
import com.zaborostroj.competitions.entities.toDto
import com.zaborostroj.competitions.repositories.UsersRepository
import org.springframework.stereotype.Component

@Component
class UsersService (
    private val usersRepository: UsersRepository
) {
    fun getAllUsers(): List<UserDto> {
        return usersRepository.findAll()
            .map(User::toDto)
    }
}