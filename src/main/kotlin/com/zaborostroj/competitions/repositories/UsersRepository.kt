package com.zaborostroj.competitions.repositories

import com.zaborostroj.competitions.entities.User
import org.springframework.data.repository.CrudRepository

interface UsersRepository : CrudRepository<User, Long> {
    fun getUserByLogin(login : String): User?
}