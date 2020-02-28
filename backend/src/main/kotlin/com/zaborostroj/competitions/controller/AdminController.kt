package com.zaborostroj.competitions.controller

import com.zaborostroj.competitions.dto.UserResponse
import com.zaborostroj.competitions.services.UsersService
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
    lateinit var usersService: UsersService

    @GetMapping(value = ["/users"])
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @ResponseBody
    fun findAll(): List<UserResponse> {
        return usersService.findAll()
    }
}