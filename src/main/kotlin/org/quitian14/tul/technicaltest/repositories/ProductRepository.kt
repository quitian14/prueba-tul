package org.quitian14.tul.technicaltest.repositories

import org.quitian14.tul.technicaltest.model.entities.Product
import org.quitian14.tul.technicaltest.types.ProductType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.util.*

@Component
@Transactional
class ProductRepository {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    private fun mapProduct(it: Map<String, Any>) =
        Product(
            it["id"].toString(),
            it["sku"].toString(),
            it["description"].toString(),
            it["name"].toString(),
            (it["price"] as BigDecimal).toDouble(),
            ProductType.valueOf(it["type"].toString()),
            it["created_at"] as Date?,
            it["updated_at"] as Date?
        )


    fun findAll(): List<Product> {
        val result = jdbcTemplate.queryForList(
            "SELECT p.id, p.name, p.sku, p.description, p.price, p.type, p.created_at, p.updated_at  FROM product p"
        )

        if (result.isEmpty()) return listOf()

        return result.map { mapProduct(it) }
    }

    fun findById(id: String): Product? {
        val result = jdbcTemplate.queryForList(
            "SELECT p.id, p.name, p.sku, p.description, p.price, " +
                "p.type, p.created_at, p.updated_at  " +
                "FROM product p " +
                "WHERE p.id = ?", id
        )

        if (result.isEmpty()) return null

        return mapProduct(result[0])
    }

    fun create(product: Product): Int {
        return jdbcTemplate.update(
            "INSERT INTO Product(id, name, sku, description, price, created_at, type) " +
                "VALUES (?,?,?,?,?,?, ?)",
            UUID.randomUUID(),
            product.name,
            product.sku,
            product.description,
            product.price,
            product.createdAt,
            product.type.toString(),
        )
    }

    fun update(product: Product): Int {
        return jdbcTemplate.update(
            "UPDATE Product SET name =?, sku = ?, description = ?, price = ?, updated_at = now() WHERE id = ?",
            product.name,
            product.sku,
            product.description,
            product.price,
            product.id,
        )
    }

    fun delete(productId: String): Int {
        return jdbcTemplate.update(
            "DELETE FROM Product WHERE id = ?",
            productId
        )
    }
}
