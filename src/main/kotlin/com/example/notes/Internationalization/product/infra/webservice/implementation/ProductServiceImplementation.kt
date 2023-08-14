package com.example.notes.Internationalization.Product.infra.webservice.implementation

import com.example.notes.Internationalization.Product.domain.entities.Product
import com.example.notes.Internationalization.Product.domain.usecases.ProductUseCase
import com.example.notes.Internationalization.Product.domain.usecases.response.ProductArrayResponse
import com.example.notes.Internationalization.Product.domain.usecases.response.ProductResponse
import com.example.notes.Internationalization.Product.infra.webservice.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")

class ProductServiceImplementation (private val productUseCase: ProductUseCase) : ProductService {

    @PostMapping
    override fun craeteOrUpdateProduct(@RequestBody product: Product): ProductResponse {
        return productUseCase.craeteOrUpdateProduct(product)
    }

    @GetMapping("/getbyUUID/{productUUID}")
    override fun getProductByUUID(@PathVariable("productUUID") productUUID: String): ProductResponse {
        return productUseCase.getProductByUUID(productUUID)
    }

    @GetMapping("/getByCode/{productCode}")
    override fun getProductByCode(@PathVariable("productCode") productCode: Int): ProductResponse {
        return productUseCase.getProductByCode(productCode)
    }

    @GetMapping
    override fun listAllProducts(): ProductArrayResponse {
        return productUseCase.listAllProducts()
    }
}