package com.zaborostroj.competitions.repositories

import com.zaborostroj.competitions.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import javax.transaction.Transactional

interface UsersRepository : JpaRepository<User, Long> {

    fun findByLoginOrEmail(@Param("login") login: String, @Param("email") email: String): List<User>?

    fun findByLogin(@Param("login") login : String): User?

    @Transactional
    fun deleteByLogin(@Param("login") login: String)
}