package com.zaborostroj.competitions.services

import com.zaborostroj.competitions.dto.JwtProvider
import com.zaborostroj.competitions.dto.JwtResponse
import com.zaborostroj.competitions.dto.LoginUser
import com.zaborostroj.competitions.dto.NewUser
import com.zaborostroj.competitions.dto.UserResponse
import com.zaborostroj.competitions.dto.toDto
import com.zaborostroj.competitions.entities.User
import com.zaborostroj.competitions.exceptions.UserNotFoundException
import com.zaborostroj.competitions.exceptions.UserWithoutRolesException
import com.zaborostroj.competitions.repositories.RolesRepository
import com.zaborostroj.competitions.repositories.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UsersService {

    @Autowired
    lateinit var usersRepository: UsersRepository

    @Autowired
    lateinit var rolesRepository: RolesRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var jwtProvider: JwtProvider

    fun findAll(): List<UserResponse> {
        return usersRepository.findAll().map(User::toDto)
    }

    fun findByLogin(login: String): UserResponse {
        val user = usersRepository.findByLogin(login)
            ?: throw UserNotFoundException("User with login = $login not found")

        if (user.roles.isEmpty()) {
            throw UserWithoutRolesException("User with id = ${user.id} has no roles")
        }

        return user.toDto()
    }

    fun findByLoginOrByEmail(login: String, email: String): List<UserResponse> {
        return usersRepository.findByLoginOrEmail(login, email)
            ?.map(User::toDto) ?: emptyList()
    }

    fun createUser(newUser: NewUser): UserResponse {
        val user = User(
            login = newUser.login,
            firstName = newUser.firstName,
            lastName = newUser.lastName,
            email = newUser.email,
            enabled = true,
            password = passwordEncoder.encode(newUser.password),
            roles = listOf(rolesRepository.findByName("ROLE_USER"))
        )

        return usersRepository.save(user).toDto()
    }

    fun login(loginUser: LoginUser): JwtResponse? {
        val userCandidate: User = usersRepository.findByLogin(loginUser.login) ?: return null

        SecurityContextHolder.getContext().authentication =
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(loginUser.login, loginUser.password))
        val jwt: String = jwtProvider.generateJwtToken(userCandidate.login)
        val authorities: List<GrantedAuthority> = userCandidate.roles
            .map { role -> SimpleGrantedAuthority(role.name) }

        return JwtResponse(jwt, userCandidate.login, authorities)
    }
}