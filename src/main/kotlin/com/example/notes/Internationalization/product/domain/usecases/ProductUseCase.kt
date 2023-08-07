package com.example.notes.Internationalization.product.domain.usecases

import com.example.notes.Internationalization.product.domain.entities.Product
import com.example.notes.Internationalization.product.domain.usecases.response.ProductArrayResponse
import com.example.notes.Internationalization.product.domain.usecases.response.ProductResponse

interface ProductUseCase {
    fun craeteOrUpdateProduct(product: Product) : ProductResponse
    fun getProductByUUID(productUUID : String) : ProductResponse
    fun getProductByCode(productCode : Int) : ProductResponse
    fun listAllProducts() : ProductArrayResponse
}