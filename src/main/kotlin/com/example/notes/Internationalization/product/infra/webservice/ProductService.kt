package com.example.notes.Internationalization.Product.infra.webservice

import com.example.notes.Internationalization.Product.domain.entities.Product
import com.example.notes.Internationalization.Product.domain.usecases.response.ProductArrayResponse
import com.example.notes.Internationalization.Product.domain.usecases.response.ProductResponse

interface ProductService {
    fun craeteOrUpdateProduct(product: Product) : ProductResponse
    fun getProductByUUID(productUUID : String) : ProductResponse
    fun getProductByCode(productCode : Int) : ProductResponse
    fun listAllProducts() : ProductArrayResponse
}