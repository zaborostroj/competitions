package com.zaborostroj.competitions.controller

import com.zaborostroj.competitions.dto.LoginUser
import com.zaborostroj.competitions.dto.NewUser
import com.zaborostroj.competitions.dto.ResponseMessage
import com.zaborostroj.competitions.dto.UserResponse
import com.zaborostroj.competitions.services.UsersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class AuthController {

    @Autowired
    lateinit var usersService: UsersService

    @PostMapping("/signin")
    fun authenticateUser(@Valid @RequestBody loginUser: LoginUser): ResponseEntity<*> {
        val jwtResponse = usersService.login(loginUser)

        return if (jwtResponse != null) {
            ResponseEntity.ok<Any?>(jwtResponse)
        } else {
            ResponseEntity<Any>(ResponseMessage("Invalid credentials"), HttpStatus.UNAUTHORIZED)
        }
    }

    @PostMapping("/signup")
    fun registerUser(@Valid @RequestBody newUser: NewUser): ResponseEntity<*> {
        val userCandidate: List<UserResponse> = usersService.findByLoginOrByEmail(newUser.login, newUser.email)

        return if (userCandidate.isEmpty()) {
            ResponseEntity(usersService.createUser(newUser), HttpStatus.OK)
        } else {
            ResponseEntity(ResponseMessage("User with same login and/or email already exists"), HttpStatus.BAD_REQUEST)
        }
    }
}