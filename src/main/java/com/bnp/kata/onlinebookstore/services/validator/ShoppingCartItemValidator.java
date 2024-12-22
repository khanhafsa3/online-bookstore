package com.bnp.kata.onlinebookstore.services.validator;

import org.springframework.stereotype.Component;

import com.bnp.kata.onlinebookstore.exception.UnauthorizedAccessException;
import com.bnp.kata.onlinebookstore.model.ShoppingCartItem;

@Component
public class ShoppingCartItemValidator {

    // Validates that the user is authorized to modify the cart item
    public void validateUserAuthorization(ShoppingCartItem cartItem, Long userId) {
        if (!cartItem.getUserId().equals(userId)) {
            throw new UnauthorizedAccessException("User not authorized to update this item");
        }
    }
}
