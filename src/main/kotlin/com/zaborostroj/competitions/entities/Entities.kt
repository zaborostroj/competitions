package com.zaborostroj.competitions.entities

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

const val NOT_INITIALIZED: Long = 0

@Entity
@Table(name = "users")
@SequenceGenerator(name = "UserSequence", sequenceName = "users_id_seq")
class User() : UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = NOT_INITIALIZED

    var login: String = ""
    var name: String = ""
    var pas: String = ""
    var role: String = ""
    var email: String = ""

    constructor(
        login: String,
        name: String,
        pas: String,
        role: String,
        email: String
    ) : this()

    override fun getPassword(): String {
        return pas
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return login
    }
}