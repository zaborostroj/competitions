package com.zaborostroj.competitions.exceptions

class UserNotFoundException(message: String) : RuntimeException(message)

class UserWithoutRolesException(message: String) : RuntimeException(message)