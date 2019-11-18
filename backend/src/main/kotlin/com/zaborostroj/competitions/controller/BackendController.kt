package com.zaborostroj.competitions.controller

import com.zaborostroj.competitions.entities.User
import com.zaborostroj.competitions.repositories.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api")
class BackendController {

    @Autowired
    lateinit var userRepository: UsersRepository

    @GetMapping(value = ["/usercontent"])
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @ResponseBody
    fun getUserContent(authentication: Authentication): String {
        val user: User = userRepository.getByLogin(authentication.name).get()
        return "Hello, ${user.firstName} ${user.lastName}!"
    }

    @GetMapping(value = ["/admincontent"])
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @ResponseBody
    fun getAdminContent(authentication: Authentication): List<User> {
        return userRepository.findAll()
    }
}