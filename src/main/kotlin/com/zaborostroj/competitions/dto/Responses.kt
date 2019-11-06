package com.zaborostroj.competitions.dto

import org.springframework.security.core.GrantedAuthority

class JwtResponse(
    var accessToken: String?,
    var username: String?,
    val authorities: Collection<GrantedAuthority>
)

class ResponseMessage(var message: String?)