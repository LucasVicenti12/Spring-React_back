package com.example.notes.Internationalization.Product.domain.repository

import com.example.notes.Internationalization.Product.domain.entities.Product
import java.util.UUID

interface ProductRepository {
    fun craeteOrUpdateProduct(product: Product) : Product?
    fun getProductByUUID(productUUID : UUID) : Product?
    fun getProductByCode(productCode : Int) : Product?
    fun listAllProducts() : List<Product>?
}