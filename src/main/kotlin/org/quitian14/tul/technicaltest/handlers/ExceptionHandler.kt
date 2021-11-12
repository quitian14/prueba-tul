package org.quitian14.tul.technicaltest.handlers

import org.quitian14.tul.technicaltest.exceptions.BusinessException
import org.quitian14.tul.technicaltest.exceptions.Error
import org.quitian14.tul.technicaltest.exceptions.ErrorContainer
import org.quitian14.tul.technicaltest.model.responses.ErrorResponse
import org.quitian14.tul.technicaltest.utils.MessageTranslator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.persistence.EntityNotFoundException
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ExceptionHandler {

    @Autowired
    private lateinit var messageTranslator: MessageTranslator

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(*[EntityNotFoundException::class, EmptyResultDataAccessException::class])
    fun handleNotFoundError(exception: Exception): ErrorResponse {
        exception.printStackTrace()
        return ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.message)
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleError(exception: java.lang.Exception): ErrorResponse {
        exception.printStackTrace()
        return ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.message)
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleBadRequestError(exception: HttpMessageNotReadableException): ErrorResponse {
        exception.printStackTrace()
        return ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.message)
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException::class)
    fun handleCustomError(request: HttpServletRequest, exception: BusinessException): ErrorContainer {

        val errorMessage = if (exception.message.isNullOrBlank()) {
            messageTranslator.getMessage(exception.code, exception.messageArguments)
        } else {
            exception.message!!
        }

        return ErrorContainer(Error(request.requestURI, exception.code, HttpStatus.BAD_REQUEST.value().toString(), errorMessage))
    }

    @ResponseBody
    @ExceptionHandler(org.quitian14.tul.technicaltest.security.SecurityException::class)
    fun handleSecurityError(exception: org.quitian14.tul.technicaltest.security.SecurityException): ResponseEntity<ErrorResponse> {
        exception.printStackTrace()
        return ResponseEntity(ErrorResponse(403, exception.message), HttpStatus.FORBIDDEN)
    }
}
