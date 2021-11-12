package org.quitian14.tul.technicaltest.repositories

import com.fasterxml.jackson.databind.ObjectMapper
import org.quitian14.tul.technicaltest.model.entities.Product
import org.quitian14.tul.technicaltest.model.entities.ShoppingCart
import org.quitian14.tul.technicaltest.model.entities.ShoppingCartItem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.util.Date

@Component
@Transactional
class ShoppingCartDetailRepository {

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    fun findByShoppingCartId(shoppingCartId: String): List<ShoppingCartItem> {
        val result = jdbcTemplate.queryForList(
            "SELECT d.id, d.amount, d.product, d.created_at, d.updated_at, d.shopping_cart_id  " +
                "FROM shopping_cart_detail d " +
                "WHERE di.shopping_cart_id = ?", shoppingCartId
        )

        if (result.isEmpty()) return listOf()

        return result.map {
            ShoppingCartItem(
                it["id"].toString(),
                it["amount"] as Int,
                objectMapper.readValue(it["product"].toString(), Product::class.java),
                it["shopping_cart_id"].toString(),
                it["created_at"] as Date?,
                it["updated_at"] as Date?
            )
        }
    }

    fun create(shoppingCartItem: ShoppingCartItem): Int {
       return jdbcTemplate.update(
            "INSERT INTO shopping_cart_detail(id, product, amount, created_at, shopping_cart_id) " +
                "VALUES (?,?::jsonb,?,?,?)",
           shoppingCartItem.id,
           objectMapper.writeValueAsString(shoppingCartItem.product),
           shoppingCartItem.amount,
           shoppingCartItem.createdAt,
           shoppingCartItem.shoppingCartId,
        )
    }

    fun update(shoppingCartItem: ShoppingCartItem): Int {
       return jdbcTemplate.update(
           "UPDATE shopping_cart_detail SET product = ?::jsonb, " +
               "amount = ?, updated_at = now() WHERE id = ?",
           objectMapper.writeValueAsString(shoppingCartItem.product),
           shoppingCartItem.amount,
           shoppingCartItem.id
        )
    }

    fun delete(id: String): Int {
        return jdbcTemplate.update(
            "DELETE FROM shopping_cart_detail WHERE id = ?",
            id
        )
    }
}
