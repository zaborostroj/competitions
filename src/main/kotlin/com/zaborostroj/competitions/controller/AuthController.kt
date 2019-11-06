package com.zaborostroj.competitions.controller

import com.zaborostroj.competitions.dto.JwtProvider
import com.zaborostroj.competitions.dto.JwtResponse
import com.zaborostroj.competitions.dto.LoginUser
import com.zaborostroj.competitions.dto.NewUser
import com.zaborostroj.competitions.dto.ResponseMessage
import com.zaborostroj.competitions.entities.User
import com.zaborostroj.competitions.repositories.RolesRepository
import com.zaborostroj.competitions.repositories.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Arrays
import java.util.Optional
import java.util.stream.Collectors
import javax.validation.Valid

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class AuthController {

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var usersRepository: UsersRepository

    @Autowired
    lateinit var rolesRepository: RolesRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    lateinit var jwtProvider: JwtProvider

    @PostMapping("/signin")
    fun authenticateUser(@Valid @RequestBody loginUser: LoginUser): ResponseEntity<*> {
        val userCandidate: Optional<User> = usersRepository.getByLogin(loginUser.login!!)

        if (userCandidate.isPresent) {
            val user: User = userCandidate.get()
            val authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(loginUser.login, loginUser.password))
            SecurityContextHolder.getContext().setAuthentication(authentication)
            val jwt: String = jwtProvider.generateJwtToken(user.login)
            val authorities: List<GrantedAuthority> = user.roles!!.stream()
                .map { role -> SimpleGrantedAuthority(role.name) }
                .collect(Collectors.toList<GrantedAuthority>())
            return ResponseEntity.ok(JwtResponse(jwt, user.login, authorities))
        } else {
            return ResponseEntity(ResponseMessage("User not found"), HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/signup")
    fun registerUser(@Valid @RequestBody newUser: NewUser): ResponseEntity<*> {
        val userCandidate: Optional<User> = usersRepository.getByLogin(newUser.login!!)

        if (!userCandidate.isPresent) {
            if (loginExists(newUser.login!!)) {
                return ResponseEntity(ResponseMessage("Login is already taken"), HttpStatus.BAD_REQUEST)
            } else if (emailExists(newUser.email!!)) {
                return ResponseEntity(ResponseMessage("Email is already taken"), HttpStatus.BAD_REQUEST)
            }

            val user = User(
                0,
                newUser.login!!,
                newUser.firstName,
                newUser.lastName,
                newUser.email!!,
                passwordEncoder.encode(newUser.password),
                true,
                null
            )
            user.roles = Arrays.asList(rolesRepository.findByName("ROLE_USER"))

            usersRepository.save(user)

            return ResponseEntity(ResponseMessage("User registered"), HttpStatus.OK)
        } else {
            return ResponseEntity(ResponseMessage("User already exists"), HttpStatus.BAD_REQUEST)
        }
    }

    private fun loginExists(login: String): Boolean {
        return usersRepository.existsByLogin(login)
    }

    private fun emailExists(email: String): Boolean {
        return usersRepository.getByEmail(email).isPresent
    }
}