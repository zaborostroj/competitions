package com.zaborostroj.competitions.repositories

import com.zaborostroj.competitions.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import java.util.Optional
import javax.transaction.Transactional

interface UsersRepository : JpaRepository<User, Long> {

    fun existsByLogin(@Param("login") login: String): Boolean

    fun getByLogin(@Param("login") login : String): Optional<User>

    fun getByEmail(@Param("email") email: String): Optional<User>

    @Transactional
    fun deleteByLogin(@Param("login") login: String)
}