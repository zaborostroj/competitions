package com.zaborostroj.competitions.controller

import com.zaborostroj.competitions.entities.User
import com.zaborostroj.competitions.repositories.UsersRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
class AdminControllerTest {

    @InjectMocks
    lateinit var adminController: AdminController

    @Mock
    lateinit var usersRepository: UsersRepository

    @Test
    fun getAllUsersTest() {
        // given
        val user1 = User(
            login = "user1",
            firstName = "name1",
            lastName = "lastname1",
            email = "email1",
            password = "111",
            enabled = true
        )
        val user2 = User(
            login = "user2",
            firstName = "name2",
            lastName = "lastname2",
            email = "email2",
            password = "222",
            enabled = true
        )
        val expectedUsers = listOf(user1, user2)
        `when`(usersRepository.findAll()).thenReturn(expectedUsers)

        // when
        val users = adminController.getAllUsers()

        // then
        assertThat(users.size == 2)
        assertThat(users[0].login == user1.login)
    }

}
