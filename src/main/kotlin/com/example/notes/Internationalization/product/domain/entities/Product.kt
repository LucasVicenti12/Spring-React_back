package com.example.notes.Internationalization.Product.domain.entities

import java.util.UUID

class Product(
        var uuid: UUID?,
        var name: String? = "",
        var description : String? = "",
        var imageBase64: String? = "",
        var code : Int? = 0,
        var category : Category? = Category.Fluffy,
        var productionCost: Double? = 0.0,
        var validityPeriod: Int? = 0,
        var weight: Double? = 0.0,
        var width: Double? = 0.0,
        var height: Double? = 0.0,
        var length: Double? = 0.0,
        var volume: Double? = 0.0,
        var situation: Int? = 0
) {
    constructor() : this(uuid = null)
}