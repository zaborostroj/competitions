package com.zaborostroj.competitions.services

import com.zaborostroj.competitions.dto.UserDto
import com.zaborostroj.competitions.entities.User
import com.zaborostroj.competitions.entities.toDto
import com.zaborostroj.competitions.repositories.UsersRepository
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class UsersService (
    private val usersRepository: UsersRepository
) {

    fun getUserByLogin(login: String): UserDto {
        return usersRepository.getUserByLogin(login)?.toDto() ?: throw IllegalArgumentException("User with login $login not found")
    }

    fun getAllUsers(): List<UserDto> {
        return usersRepository.findAll().map(User::toDto)
    }
}