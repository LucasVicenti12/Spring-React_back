package com.example.notes.Internationalization.product.domain.usecases.implementation

import com.example.notes.Internationalization.product.domain.entities.Category
import com.example.notes.Internationalization.product.domain.entities.Product
import com.example.notes.Internationalization.product.domain.exceptions.*
import com.example.notes.Internationalization.product.domain.repository.ProductRepository
import com.example.notes.Internationalization.product.domain.usecases.ProductUseCase
import com.example.notes.Internationalization.product.domain.usecases.response.ProductArrayResponse
import com.example.notes.Internationalization.product.domain.usecases.response.ProductResponse
import org.slf4j.LoggerFactory

class ProductUseCaseImplementation (private val productRepository: ProductRepository) : ProductUseCase {

    companion object{
        private val logger = LoggerFactory.getLogger(ProductUseCaseImplementation::class.java)
    }

    override fun craeteOrUpdateProduct(product: Product): ProductResponse {
        return try {
            if(product.name === ""){
                return ProductResponse(error = PRODUCT_INVALID_NAME)
            }
            if(product.description === ""){
                return ProductResponse(error = PRODUCT_INVALID_DESCRIPTION)
            }
            if(product.code === 0){
                return ProductResponse(error = PRODUCT_INVALID_CODE)
            }
            if(product.category === null){
                return ProductResponse(error = PRODUCT_INVALID_CATEGORY)
            }
            ProductResponse(product = productRepository.craeteOrUpdateProduct(product), error = null)
        }catch (e: Exception){
            logger.error("PRODUCT", e)
            ProductResponse(error = PRODUCT_DEFAULT_ERROR)
        }
    }

    override fun getProductByUUID(productUUID: String): ProductResponse {
        TODO("Not yet implemented")
    }

    override fun getProductByCode(productCode: Int): ProductResponse {
        TODO("Not yet implemented")
    }

    override fun listAllProducts(): ProductArrayResponse {
        TODO("Not yet implemented")
    }
}