package com.zaborostroj.competitions.services

import com.zaborostroj.competitions.dto.UserDto
import com.zaborostroj.competitions.entities.User
import com.zaborostroj.competitions.entities.toDto
import com.zaborostroj.competitions.repositories.UsersRepository
import org.springframework.stereotype.Service

@Service
class UsersService (
    private val usersRepository: UsersRepository
) {

    fun getByLogin(login: String): UserDto {
        return usersRepository.getByLogin(login).toDto()
    }

    fun getAllUsers(): List<UserDto> {
        return usersRepository.findAll()
            .map(User::toDto)
    }
}