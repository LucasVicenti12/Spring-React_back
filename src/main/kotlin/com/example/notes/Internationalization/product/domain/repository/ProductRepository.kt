package com.example.notes.Internationalization.product.domain.repository

import com.example.notes.Internationalization.product.domain.entities.Product
interface ProductRepository {
    fun craeteOrUpdateProduct(product: Product) : Product?
    fun getProductByUUID(productUUID : String) : Product?
    fun getProductByCode(productCode : Int) : Product?
    fun listAllProducts() : List<Product>?
}