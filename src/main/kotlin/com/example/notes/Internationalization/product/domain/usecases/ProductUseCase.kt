package com.example.notes.Internationalization.Product.domain.usecases

import com.example.notes.Internationalization.Product.domain.entities.Product
import com.example.notes.Internationalization.Product.domain.usecases.response.ProductArrayResponse
import com.example.notes.Internationalization.Product.domain.usecases.response.ProductResponse
import java.util.UUID

interface ProductUseCase {
    fun craeteOrUpdateProduct(product: Product) : ProductResponse
    fun getProductByUUID(productUUID : UUID) : ProductResponse
    fun getProductByCode(productCode : Int) : ProductResponse
    fun listAllProducts() : ProductArrayResponse
}