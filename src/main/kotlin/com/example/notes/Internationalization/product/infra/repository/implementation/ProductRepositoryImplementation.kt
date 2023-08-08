package com.example.notes.Internationalization.Product.infra.repository.implementation

import com.example.notes.Internationalization.Product.domain.entities.Product
import com.example.notes.Internationalization.Product.domain.repository.ProductRepository
import com.example.notes.Internationalization.Product.infra.repository.database.ProductDatabase
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductRepositoryImplementation : ProductRepository {
    override fun craeteOrUpdateProduct(product: Product): Product? {
        if (product.uuid == null) {
            product.uuid = UUID.randomUUID()
            transaction {
                ProductDatabase.insert {
                    it[uuid] = product.uuid!!
                    it[name] = product.name!!
                    it[description] = product.description!!
                    it[imageBase64] = product.imageBase64!!
                    it[code] = product.code!!
                    it[category] = product.category.toString().toInt()!!
                    it[productionCost] = product.productionCost!!
                    it[validityPeriod] = product.validityPeriod!!
                    it[weight] = product.weight!!
                    it[width] = product.width!!
                    it[height] = product.height!!
                    it[length] = product.length!!
                    it[volume] = product.volume!!
                    it[situation] = product.situation!!
                }
                product
            }
        } else {
            transaction {
                ProductDatabase.update({ Op.build { ProductDatabase.uuid eq product.uuid!! } }) {
                    it[name] = product.name!!
                    it[description] = product.description!!
                    it[imageBase64] = product.imageBase64!!
                    it[category] = product.category.toString().toInt()!!
                    it[productionCost] = product.productionCost!!
                    it[validityPeriod] = product.validityPeriod!!
                    it[weight] = product.weight!!
                    it[width] = product.width!!
                    it[height] = product.height!!
                    it[length] = product.length!!
                    it[volume] = product.volume!!
                    it[situation] = product.situation!!
                }
            }
        }
        return product
    }

    override fun getProductByUUID(productUUID: UUID): Product? {
        return transaction {
           ProductDatabase.select { ProductDatabase.uuid eq productUUID }.map {
               Product(
                       uuid = it[ProductDatabase.uuid],
                       name = it[ProductDatabase.name],
                       description = it[ProductDatabase.description],
                       imageBase64 = it[ProductDatabase.imageBase64],
                       code = it[ProductDatabase.code],
                       category = it[ProductDatabase.category],
                       productionCost = it[ProductDatabase.productionCost],
                       validityPeriod = it[ProductDatabase.validityPeriod],
                       weight = it[ProductDatabase.weight],
                       width = it[ProductDatabase.width],
                       height = it[ProductDatabase.height],
                       length = it[ProductDatabase.length],
                       volume = it[ProductDatabase.volume],
                       situation = it[ProductDatabase.situation]
               )
           }.firstOrNull()
        }
    }

    override fun getProductByCode(productCode: Int): Product? {
        TODO("Not yet implemented")
    }

    override fun listAllProducts(): List<Product>? {
        TODO("Not yet implemented")
    }
}