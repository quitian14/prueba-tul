package org.quitian14.tul.technicaltest.utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.MessageSource
import org.springframework.stereotype.Component
import java.util.Locale

@Component
class MessageTranslator {

    @Value("\${spring.mvc.locale}")
    lateinit var locale: String

    @Autowired
    private lateinit var messageSource: MessageSource

    fun getMessage(code: String, args: Array<Any>? = null, language: String? = null): String {
        return messageSource.getMessage(code, args, Locale((language ?: locale).split("_").last()))
    }
}
