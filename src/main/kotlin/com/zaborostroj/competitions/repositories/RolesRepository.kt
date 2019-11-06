package com.zaborostroj.competitions.repositories

import com.zaborostroj.competitions.entities.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface RolesRepository : JpaRepository<Role, Long> {

    fun findByName(@Param("name") name: String): Role
}