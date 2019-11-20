package com.zaborostroj.competitions.services

import com.zaborostroj.competitions.exceptions.UserNotFoundException
import com.zaborostroj.competitions.exceptions.UserWithoutRolesException
import com.zaborostroj.competitions.repositories.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl : UserDetailsService {

    @Autowired
    lateinit var usersRepository: UsersRepository

    override fun loadUserByUsername(login: String): UserDetails {
        val user = usersRepository.findByLogin(login)
            ?: throw UserNotFoundException("User with username = $login not found")

        if (user.roles.isEmpty()) {
            throw UserWithoutRolesException("User with id = ${user.id} has no roles")
        }

        val authorities: List<GrantedAuthority> = user.roles
            .map { role -> SimpleGrantedAuthority(role.name) }

        return org.springframework.security.core.userdetails.User
            .withUsername(login)
            .password(user.password)
            .authorities(authorities)
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .build()
    }
}