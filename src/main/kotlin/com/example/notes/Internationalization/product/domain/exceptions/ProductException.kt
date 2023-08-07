package com.example.notes.Internationalization.product.domain.exceptions

import com.example.notes.core.shared.error.GenericError

val PRODUCT_INVALID_NAME = ProductException("PRODUCT_INVALID_NAME", "The name is invalid!")
val PRODUCT_INVALID_DESCRIPTION = ProductException("PRODUCT_INVALID_DESCRIPTION", "The description is invalid!")
val PRODUCT_CODE_ALREADY_EXISTS = ProductException("PRODUCT_CODE_ALREADY_EXISTS", "The code of product already exists!")
val PRODUCT_INVALID_CATEGORY = ProductException("PRODUCT_INVALID_CATEGORY", "You need to inform the category!")
val PRODUCT_INVALID_VALIDITY_PERIOD = ProductException("PRODUCT_INVALID_VALIDITY_PERIOD", "The validity period is invalid!")
val PRODUCT_WEIGHT_INVALID = ProductException("PRODUCT_WEIGHT_INVALID", "The weight is invalid!")
val PRODUCT_WIDTH_INVALID = ProductException("PRODUCT_WIDTH_INVALID", "The width is invalid!")
val PRODUCT_HEIGHT_INVALID = ProductException("PRODUCT_HEIGHT_INVALID", "The height is invalid!")
val PRODUCT_LENGTH_INVALID = ProductException("PRODUCT_LENGTH_INVALID", "The length is invalid!")
val PRODUCT_VOLUME_INVALID = ProductException("PRODUCT_VOLUME_INVALID", "The volume is invalid!")
val PRODUCT_INVALID_CODE = ProductException("PRODUCT_INVALID_CODE", "The code is invalid!")
val PRODUCT_DEFAULT_ERROR = ProductException("PRODUCT_DEFAULT_ERROR", "An unexpected error has ocurred!")

class ProductException (code: String, description: String) : GenericError("product-module", code, description)