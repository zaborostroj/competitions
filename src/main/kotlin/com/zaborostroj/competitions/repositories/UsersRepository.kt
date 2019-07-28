package com.zaborostroj.competitions.repositories

import com.zaborostroj.competitions.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UsersRepository : JpaRepository<User, Long> {
//    fun getAllUsers(): List<User>
}