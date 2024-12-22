package com.bnp.kata.onlinebookstore.services.validator;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.bnp.kata.onlinebookstore.exception.UnauthorizedAccessException;
import com.bnp.kata.onlinebookstore.model.ShoppingCartItem;

public class ShoppingCartItemValidatorTest {

	private ShoppingCartItemValidator shoppingCartItemValidator;

	@BeforeEach
	public void setUp() {
		shoppingCartItemValidator = new ShoppingCartItemValidator();
	}

	@Test
	public void testValidateUserAuthorization_ShouldNotThrow_WhenUserIsAuthorized() {
		ShoppingCartItem cartItem = new ShoppingCartItem();
		cartItem.setUserId(1L);
		assertDoesNotThrow(() -> shoppingCartItemValidator.validateUserAuthorization(cartItem, 1L));
	}

	@Test
	public void testValidateUserAuthorization_ShouldThrow_WhenUserIsNotAuthorized() {
		ShoppingCartItem cartItem = new ShoppingCartItem();
		cartItem.setUserId(1L);
		UnauthorizedAccessException exception = assertThrows(UnauthorizedAccessException.class,
				() -> shoppingCartItemValidator.validateUserAuthorization(cartItem, 2L));
		assertEquals("User not authorized to update this item", exception.getMessage());
	}

	@Test
	public void testValidateUserAuthorization_ShouldThrow_WhenCartItemIsNull() {
		Long userId = 1L;
		assertThrows(NullPointerException.class,
				() -> shoppingCartItemValidator.validateUserAuthorization(null, userId));
	}

}