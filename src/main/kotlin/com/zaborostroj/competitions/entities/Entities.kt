package com.zaborostroj.competitions.entities

import java.time.Instant
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

const val NOT_INITIALIZED: Long = 0

@Entity
@Table(name = "users")
@SequenceGenerator(name = "UserSequence", sequenceName = "users_id_seq")
class User(
    val login: String,
    val name: String,
    val role: String,
    val email: String,
    val deletedAt: Instant? = null
) {
    @Id
    val id: Long = NOT_INITIALIZED
}