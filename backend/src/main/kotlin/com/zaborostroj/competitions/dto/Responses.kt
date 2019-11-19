package com.zaborostroj.competitions.dto

import org.springframework.security.core.GrantedAuthority

data class JwtResponse(
    var accessToken: String?,
    var username: String?,
    val authorities: Collection<GrantedAuthority>
)

data class ResponseMessage(var message: String?)

data class UserResponse(
    var login: String,
    var firstName: String?,
    var lastName: String?,
    var email: String,
    var enabled: Boolean = false,
    var roles: List<String>?
)