package org.quitian14.tul.technicaltest.exceptions

class BusinessException(var code: String, override var message: String? = null, var messageArguments: Array<Any>? = null) :
    RuntimeException(message)
