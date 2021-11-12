package org.quitian14.tul.technicaltest.repositories

import com.fasterxml.jackson.databind.ObjectMapper
import org.quitian14.tul.technicaltest.model.entities.Product
import org.quitian14.tul.technicaltest.model.entities.ShoppingCart
import org.quitian14.tul.technicaltest.model.entities.ShoppingCartItem
import org.quitian14.tul.technicaltest.types.ShoppingCartState
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.Date

@Component
@Transactional
class ShoppingCartRepository(
    @Autowired var jdbcTemplate: JdbcTemplate,
    @Autowired var objectMapper: ObjectMapper
) {
    private fun mapShoppingCart(it: Map<String, Any>) = ShoppingCart(
        it["id"].toString(),
        ShoppingCartState.valueOf(it["state"].toString()),
        it["created_at"] as Date?,
        it["updated_at"] as Date?
    )

    fun findAll(): List<ShoppingCart> {
        val result = jdbcTemplate.queryForList(
            "SELECT p.id, p.state, p.sku, p.total, p.created_at, p.updated_at " +
                "FROM shopping_cart p"
        )

        if (result.isEmpty()) return listOf()

        return result.map { mapShoppingCart(it) }
    }

    fun findById(id: String): ShoppingCart? {
        val result = jdbcTemplate.queryForList(
            "SELECT sc.id, sc.state, sc.total, sc.created_at, sc.updated_at, " +
                "d.id as itemId, d.amount, d.product, d.created_at, d.updated_at, d.shopping_cart_id " +
                "FROM shopping_cart sc " +
                "LEFT JOIN shopping_cart_detail d ON (d.shopping_cart_id = sc.id ) " +
                "WHERE sc.id = ? ", id
        )

        if (result.isEmpty()) return null

        val shoppingCart = mapShoppingCart(result[0])

        val items = if (result[0]["itemId"] != null)
            result.map {
                ShoppingCartItem(
                    it["itemId"].toString(),
                    it["amount"] as Int,
                    objectMapper.readValue(it["product"].toString(), Product::class.java),
                    it["shopping_cart_id"].toString(),
                    it["created_at"] as Date?,
                    it["updated_at"] as Date?
                )
            }
        else listOf()

        shoppingCart.items = items.toMutableList()

        return shoppingCart
    }

    fun create(shoppingCart: ShoppingCart): Int {
        return jdbcTemplate.update(
            "INSERT INTO shopping_cart(id, state, total, created_at) " +
                "VALUES (?,?,?,?)",
            shoppingCart.id,
            shoppingCart.state.toString(),
            shoppingCart.getTotal(),
            shoppingCart.createdAt
        )
    }

    fun update(shoppingCart: ShoppingCart): Int {
        return jdbcTemplate.update(
            "UPDATE shopping_cart SET state = ?, total = ?, updated_at = now() WHERE id = ?",
            shoppingCart.state.toString(),
            shoppingCart.getTotal(),
            shoppingCart.id,
        )
    }
}
