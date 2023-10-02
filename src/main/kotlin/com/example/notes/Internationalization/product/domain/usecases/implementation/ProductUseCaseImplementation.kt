package com.example.notes.Internationalization.Product.domain.usecases.implementation

import com.example.notes.Internationalization.Product.domain.entities.Product
import com.example.notes.Internationalization.Product.domain.exceptions.*
import com.example.notes.Internationalization.Product.domain.repository.ProductRepository
import com.example.notes.Internationalization.Product.domain.usecases.ProductUseCase
import com.example.notes.Internationalization.Product.domain.usecases.response.ProductArrayResponse
import com.example.notes.Internationalization.Product.domain.usecases.response.ProductResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ProductUseCaseImplementation(private val productRepository: ProductRepository) : ProductUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(ProductUseCaseImplementation::class.java)
    }

    override fun craeteOrUpdateProduct(product: Product): ProductResponse {
        return try {
            if (product.name == "") {
                return ProductResponse(error = PRODUCT_INVALID_NAME)
            }
            if (product.description == "" || product.description == null) {
                return ProductResponse(error = PRODUCT_INVALID_DESCRIPTION)
            }
            if (product.code == 0 || product.code == null) {
                return ProductResponse(error = PRODUCT_INVALID_CODE)
            }
            if (product.width == 0.0 || product.width == null) {
                return ProductResponse(error = PRODUCT_WIDTH_INVALID)
            }
            if (product.weight == 0.0 || product.weight == null) {
                return ProductResponse(error = PRODUCT_WEIGHT_INVALID)
            }
            if (product.length == 0.0 || product.length == null) {
                return ProductResponse(error = PRODUCT_LENGTH_INVALID)
            }
            if (product.height == 0.0 || product.height == null) {
                return ProductResponse(error = PRODUCT_HEIGHT_INVALID)
            }
            if (product.volume == 0.0 || product.volume == null) {
                return ProductResponse(error = PRODUCT_VOLUME_INVALID)
            }
            if (product.validityPeriod == 0 || product.validityPeriod == null) {
                return ProductResponse(error = PRODUCT_INVALID_VALIDITY_PERIOD)
            }
            if (productRepository.getProductByCode(product.code!!) != null){
                return ProductResponse(error = PRODUCT_CODE_ALREADY_EXISTS)
            }
            if (product.category == null) {
                return ProductResponse(error = PRODUCT_INVALID_CATEGORY)
            }
            ProductResponse(product = productRepository.craeteOrUpdateProduct(product), error = null)
        } catch (e: Exception) {
            logger.error("PRODUCT", e)
            ProductResponse(error = PRODUCT_DEFAULT_ERROR)
        }
    }

    override fun getProductByUUID(productUUID: UUID): ProductResponse {
        return try {
            ProductResponse(product = productRepository.getProductByUUID(productUUID), error = null)
        } catch (e: Exception) {
            ProductResponse(error = PRODUCT_DEFAULT_ERROR)
        }
    }

    override fun getProductByCode(productCode: Int): ProductResponse {
        return try {
            ProductResponse(product = productRepository.getProductByCode(productCode), error = null)
        } catch (e: Exception) {
            ProductResponse(error = PRODUCT_DEFAULT_ERROR)
        }
    }

    override fun listAllProducts(): ProductArrayResponse {
        return try {
            ProductArrayResponse(productRepository.listAllProducts(), error = null)
        } catch (e: Exception) {
            ProductArrayResponse(error = PRODUCT_DEFAULT_ERROR)
        }
    }
}