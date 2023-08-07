package com.example.notes.Internationalization.product.domain.usecases.response

import com.example.notes.Internationalization.product.domain.entities.Product
import com.example.notes.Internationalization.product.domain.exceptions.ProductException

data class ProductResponse (val product: Product? = null, val error: ProductException? = null)

data class ProductArrayResponse(val products : List<Product>? = listOf(), val error: ProductException? = null)