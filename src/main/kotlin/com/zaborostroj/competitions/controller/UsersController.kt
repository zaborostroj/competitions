package com.zaborostroj.competitions.controller

import com.zaborostroj.competitions.dto.UserDto
import com.zaborostroj.competitions.services.UsersService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Api(tags = ["Users"], description = "Manage application users")
class UsersController (
    private val usersService: UsersService
) {
    @ApiOperation("Get users")
    @GetMapping("/users")
    fun getUsers(): List<UserDto> {
        return usersService.getAllUsers()
    }
}