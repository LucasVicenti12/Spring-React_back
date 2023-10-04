package com.example.notes.Internationalization.Product.infra.repository.database

import org.jetbrains.exposed.sql.Table

object ProductDatabase : Table("product"){
    var uuid = uuid("product_uuid").uniqueIndex()
    var name = varchar("product_name", 250)
    var description = text("product_description")
    var imageBase64 = text("product_image_base64")
    var code = integer("product_code").uniqueIndex()
    var category = integer("product_category")
    var productionCost = double("product_cost")
    var validityPeriod = integer("product_vality_period")
    var weight = double("product_weight")
    var width = double("product_width")
    var height = double("product_height")
    var length = double("product_length")
    var volume = double("product_volume")
    var situation = integer("product_situation")

    init {
        PrimaryKey(code)
    }
}