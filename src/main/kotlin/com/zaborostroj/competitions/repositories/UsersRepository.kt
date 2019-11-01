package com.zaborostroj.competitions.repositories

import com.zaborostroj.competitions.entities.User
import org.springframework.data.repository.CrudRepository

interface UsersRepository : CrudRepository<User, Long> {
    fun getByLogin(login : String): User
//    fun getAllUsers(): List<User>
}