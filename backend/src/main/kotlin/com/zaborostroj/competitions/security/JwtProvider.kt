package com.zaborostroj.competitions.security

import com.zaborostroj.competitions.repositories.UsersRepository
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.SignatureException
import io.jsonwebtoken.UnsupportedJwtException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtProvider {

    @Autowired
    lateinit var usersRepository: UsersRepository

    @Value("\${assm.app.jwtSecret}")
    lateinit var jwtSecret: String

    @Value("\${assm.app.jwtExpiration}")
    var jwtExpiration: Int? = 0

    fun generateJwtToken(login: String): String {
        return Jwts.builder()
            .setSubject(login)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().getTime() + jwtExpiration!! * 1000))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    fun validateJwtToken(authToken: String): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (e: SignatureException) {
            logger.error("Invalid JWT signature -> Message: {} ", e)
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token -> Message: {}", e)
        } catch (e: ExpiredJwtException) {
            logger.error("Expired JWT token -> Message: {}", e)
        } catch (e: UnsupportedJwtException) {
            logger.error("Unsupported JWT token -> Message: {}", e)
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty -> Message: {}", e)
        }
        return false
    }

    companion object {
        private val logger = LoggerFactory.getLogger(JwtProvider::class.java)
    }

    fun getUserNameFromJwtToken(token: String): String {
        return Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .body
            .subject
    }
}