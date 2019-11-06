package com.zaborostroj.competitions.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

class LoginUser : Serializable {

    @JsonProperty("login")
    var login: String? = null

    @JsonProperty("password")
    var password: String? = null

    constructor()

    constructor(
        login: String,
        password: String
    ) {
        this.login = login
        this.password = password
    }

    companion object {
        private const val serialVersionUID = -1764970284520387975L
    }
}

class NewUser : Serializable {

    @JsonProperty("login")
    var login: String? = null

    @JsonProperty("firstName")
    var firstName: String? = null

    @JsonProperty("lastName")
    var lastName: String? = null

    @JsonProperty("email")
    var email: String? = null

    @JsonProperty("password")
    var password: String? = null

    constructor()

    constructor(
        login: String,
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ) {
        this.login = login
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
    }

    companion object {
        private const val serialVersionUID = -1764970284520387975L
    }
}