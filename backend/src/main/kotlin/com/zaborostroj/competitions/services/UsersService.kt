package com.zaborostroj.competitions.services

import com.zaborostroj.competitions.dto.UserResponse
import com.zaborostroj.competitions.dto.toDto
import com.zaborostroj.competitions.entities.User
import com.zaborostroj.competitions.repositories.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UsersService {

    @Autowired
    lateinit var usersRepository: UsersRepository

    fun findAll(): List<UserResponse> {
        return usersRepository.findAll().map(User::toDto)
    }
}