package com.zaborostroj.competitions.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.security.crypto.password.StandardPasswordEncoder
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter () {

    @Autowired
    lateinit var dataSource : DataSource

    override fun configure(auth : AuthenticationManagerBuilder) {
        auth
            .jdbcAuthentication()
            .dataSource(dataSource)
//            .usersByUsernameQuery("""
//                select login, password, deleted_at from users
//                where login = ?
//            """.trimIndent())
            .passwordEncoder(StandardPasswordEncoder())
    }
}