package com.zaborostroj.competitions.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.SequenceGenerator
import javax.persistence.Table

const val NOT_INITIALIZED: Long = 0

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserSequence")
    @SequenceGenerator(name = "UserSequence", sequenceName = "users_id_seq")
    val id: Long = NOT_INITIALIZED,

    @Column(name = "login")
    val login: String,

    @Column(name = "first_name")
    val firstName: String?,

    @Column(name = "last_name")
    val lastName: String?,

    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "enabled")
    val enabled: Boolean = false,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles: Collection<Role>? = null
)

@Entity
@Table(name = "roles")
data class Role (

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RoleSequence")
    @SequenceGenerator(name = "RoleSequence", sequenceName = "roles_id_seq")
    val id: Long,

    @Column(name = "name")
    val name: String
)
