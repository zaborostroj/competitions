package com.zaborostroj.competitions.controller

import com.zaborostroj.competitions.entities.User
import com.zaborostroj.competitions.repositories.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
class AdminController {

    @Autowired
    lateinit var usersRepository: UsersRepository

    @GetMapping(value = ["/users"])
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @ResponseBody
    fun getAllUsers(): List<User> {
        return usersRepository.findAll()
    }
}