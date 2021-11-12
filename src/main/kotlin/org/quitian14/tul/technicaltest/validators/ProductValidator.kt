package org.quitian14.tul.technicaltest.validators

import org.quitian14.tul.technicaltest.constants.ErrorCode.FIELD_NOT_FOUND_ERROR
import org.quitian14.tul.technicaltest.constants.ErrorCode.GENERIC_NOT_FOUND_ERROR
import org.quitian14.tul.technicaltest.exceptions.BusinessException
import org.quitian14.tul.technicaltest.model.request.CreateProductRequest
import org.quitian14.tul.technicaltest.types.ProductType
import org.springframework.stereotype.Component

@Component
class ProductValidator {

    @Throws(BusinessException::class)
    fun validate(request: CreateProductRequest) {
        when {
            (request.type == null) -> error("type", FIELD_NOT_FOUND_ERROR)
            (!ProductType.isValidType(request.type)) -> error(
                "type: ${request.type.toLowerCase()}, values: ${ProductType.values().toList()}".toLowerCase(),
                GENERIC_NOT_FOUND_ERROR

            )
        }
    }

    fun error(field: String, type: String = FIELD_NOT_FOUND_ERROR) {
        throw BusinessException(
            type,
            messageArguments = arrayOf(field)
        )
    }
}
