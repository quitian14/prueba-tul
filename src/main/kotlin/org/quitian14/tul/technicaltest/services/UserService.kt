package org.quitian14.tul.technicaltest.services

import org.quitian14.tul.technicaltest.model.entities.User
import org.quitian14.tul.technicaltest.repositories.UserRepository
import org.quitian14.tul.technicaltest.utils.EncryptorUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var encryptorUtil: EncryptorUtil

    fun create(user: User) {
        user.password = encryptorUtil.encrypt(user.password!!)
        userRepository.createUser(user)
    }
}
