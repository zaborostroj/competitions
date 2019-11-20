package com.zaborostroj.competitions.controller

import com.zaborostroj.competitions.dto.UserResponse
import com.zaborostroj.competitions.services.UsersService
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
    lateinit var usersService: UsersService

    @GetMapping(value = ["/usercontent"])
    @PreAuthorize(value = "hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @ResponseBody
    fun getUserContent(authentication: Authentication): String {
        val userResponse: UserResponse = usersService.findByLogin(authentication.name)
        return "Hello, ${userResponse.firstName} ${userResponse.lastName}!"
    }

    @GetMapping(value = ["/admincontent"])
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @ResponseBody
    fun getAdminContent(authentication: Authentication): List<UserResponse> {
        return usersService.findAll()
    }
}