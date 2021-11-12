package org.quitian14.tul.technicaltest.controllers

import org.quitian14.tul.technicaltest.constants.Routes
import org.quitian14.tul.technicaltest.model.entities.User
import org.quitian14.tul.technicaltest.model.request.CreateUserRequest
import org.quitian14.tul.technicaltest.model.request.LoginRequest
import org.quitian14.tul.technicaltest.security.Secured
import org.quitian14.tul.technicaltest.services.SecurityService
import org.quitian14.tul.technicaltest.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Routes.USER)
class UserController {

    @Autowired
    lateinit var securityService: SecurityService

    @Autowired
    lateinit var userService: UserService

    @PostMapping(Routes.LOGIN)
    fun login(@RequestBody @Validated loginRequest: LoginRequest) =
        securityService.login(loginRequest.user, loginRequest.password)

    @Secured(permissions = ["create"])
    @PostMapping
    fun create(@RequestBody @Validated req: CreateUserRequest) {
        val user = User(req.userName, req.mail, req.name, req.password)

        userService.create(user)
    }
}
