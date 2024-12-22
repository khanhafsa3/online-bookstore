package com.bnp.kata.onlinebookstore.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bnp.kata.onlinebookstore.dto.ShoppingCartRequest;
import com.bnp.kata.onlinebookstore.model.ShoppingCartItem;
import com.bnp.kata.onlinebookstore.services.ShoppingCartService;
@RestController
@RequestMapping("/api/v1/cart")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@PostMapping("/add")
	public ResponseEntity<ShoppingCartItem> addBookToCart(@RequestBody ShoppingCartRequest cartRequest) {
		ShoppingCartItem addedItem = shoppingCartService.addBookToCart(cartRequest.getBookId(), cartRequest.getUserId(),
				cartRequest.getQuantity());
		return ResponseEntity.ok(addedItem);
	}

	@PutMapping("/update")
	public ResponseEntity<ShoppingCartItem> updateBookQuantity(@RequestBody ShoppingCartRequest cartRequest) {
		ShoppingCartItem updatedItem = shoppingCartService.updateBookQuantity(cartRequest);
		return ResponseEntity.ok(updatedItem);
	}
	
	 @DeleteMapping("/remove")
	    public ResponseEntity<Void> removeBookFromCart(@RequestParam Long bookId, @RequestParam Long userId) {
	        shoppingCartService.removeBookFromCart(bookId, userId);
	        return ResponseEntity.noContent().build();
	    }
	 
	 @GetMapping("/user/{userId}")
	    public ResponseEntity<List<ShoppingCartItem>> getCartItemsByUserId(@PathVariable Long userId) {
	        List<ShoppingCartItem> cartItems = shoppingCartService.findCartItemsByUserId(userId);
	        return ResponseEntity.ok(cartItems);
	    }
}
