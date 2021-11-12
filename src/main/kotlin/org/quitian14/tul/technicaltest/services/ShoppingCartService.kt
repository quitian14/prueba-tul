package org.quitian14.tul.technicaltest.services

import org.quitian14.tul.technicaltest.constants.ErrorCode
import org.quitian14.tul.technicaltest.exceptions.BusinessException
import org.quitian14.tul.technicaltest.model.entities.ShoppingCart
import org.quitian14.tul.technicaltest.model.entities.ShoppingCartItem
import org.quitian14.tul.technicaltest.repositories.ProductRepository
import org.quitian14.tul.technicaltest.repositories.ShoppingCartDetailRepository
import org.quitian14.tul.technicaltest.repositories.ShoppingCartRepository
import org.quitian14.tul.technicaltest.types.ShoppingCartState
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class ShoppingCartService(
    @Autowired var shoppingCartRepository: ShoppingCartRepository,
    @Autowired var shoppingCartDetailRepository: ShoppingCartDetailRepository,
    @Autowired var productRepository: ProductRepository
) {
    fun create(): ShoppingCart {
        val shoppingCart = ShoppingCart()
        shoppingCartRepository.create(shoppingCart)
        return shoppingCart
    }

    fun addProduct(shoppingCartId: String, amount: Int, productId: String): ShoppingCart {
        val product = productRepository.findById(productId) ?: throw EntityNotFoundException("Product not found")
        val shoppingCart = findById(shoppingCartId)

        if (shoppingCart.state == ShoppingCartState.COMPLETE) throw BusinessException(ErrorCode.SHOPPING_CART_COMPLETED)

        val shoppingCartItem = ShoppingCartItem(
            amount = amount,
            product = product,
            shoppingCartId = shoppingCartId,
        )

        val shoppingCartFoundItem = shoppingCart.getItem(productId)

        val item = shoppingCart.addItem(shoppingCartItem)
        shoppingCartRepository.update(shoppingCart)

        if (shoppingCartFoundItem == null) {
            shoppingCartDetailRepository.create(item)
        } else {
            shoppingCartDetailRepository.update(item)
        }

        return shoppingCart
    }

    fun removeProduct(shoppingCartId: String, productId: String): ShoppingCart {
        val shoppingCart = findById(shoppingCartId)

        if (shoppingCart.state == ShoppingCartState.COMPLETE) throw BusinessException(ErrorCode.SHOPPING_CART_COMPLETED)

        val shoppingCartItem = shoppingCart.removeItem(productId)?: throw EntityNotFoundException("Product not found")

        shoppingCartDetailRepository.delete(shoppingCartItem.id)
        shoppingCartRepository.update(shoppingCart)

        return shoppingCart
    }

    fun updateProduct(shoppingCartId: String, productId: String, amount: Int): ShoppingCart {
        val shoppingCart = findById(shoppingCartId)

        if (shoppingCart.state == ShoppingCartState.COMPLETE) throw BusinessException(ErrorCode.SHOPPING_CART_COMPLETED)

        val shoppingCartItem = shoppingCart.updateItem(productId, amount)
            ?: throw EntityNotFoundException("Product not found")

        shoppingCartDetailRepository.update(shoppingCartItem)
        shoppingCartRepository.update(shoppingCart)

        return shoppingCart
    }

    fun findById(id: String) =
        shoppingCartRepository.findById(id) ?: throw EntityNotFoundException("ShoppingCart not found")

    fun completeShoppingCart(id: String): ShoppingCart {
        val shoppingCart = findById(id)

        if (shoppingCart.state == ShoppingCartState.COMPLETE) throw BusinessException(ErrorCode.SHOPPING_CART_COMPLETED)

        shoppingCart.state = ShoppingCartState.COMPLETE

        shoppingCartRepository.update(shoppingCart)

        return shoppingCart
    }
}
