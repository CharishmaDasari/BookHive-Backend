package com.example.CartService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CartService.model.Cart;
import com.example.CartService.exceptions.CartWithTheIDAlreadyPresentException;
import com.example.CartService.exceptions.CartWithTheIdNotPresentException;
import com.example.CartService.service.CartService;

/* Annotate the class with @RestController and @RequestMapping */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class CartController {
	
	/*Constructor based Autowiring should be implemented */

	
	@Autowired
	private CartService cartService;
	
	/*
     * Define a handler method to get all the carts. This handler method should map to
     * the URL "/cart". This method will return the List of carts in the response body
     *  with the Httpstatus OK.
     */
	
	
	@PostMapping(value="/cart")
	public ResponseEntity<?> createNewCartHandler(@RequestBody Cart cart){
		
		ResponseEntity<?> responseEntity;
			
		try {
				Cart newCart;
				newCart = cartService.createNewCart(cart);
				responseEntity = new ResponseEntity<Cart>(newCart, HttpStatus.CREATED);
			} catch (CartWithTheIDAlreadyPresentException e) {
				// TODO Auto-generated catch block
				responseEntity = new ResponseEntity<String>("A cart with this ID cannot be found.", HttpStatus.NOT_FOUND);
			}
		
		return responseEntity;
	}
	
	 /*
     *  Handler method to add a new cart. This handler method should map to
     * the URL "/cart". This method will return the saved movie in the response body
     *  with the Httpstatus CREATED, if the cart is saved successfully, else, returns the message
     * "cart already Exist" with the status CONFLICT.
     */

	
	@GetMapping(value="/cart/{cartId}")
	public ResponseEntity<?> getCartByIdHandler(@PathVariable("cartId") int id){
		
		ResponseEntity<?> responseEntity;
		
		try {
			Cart cart = cartService.getCartById(id);
			responseEntity = new ResponseEntity<Cart>(cart, HttpStatus.OK);	
		}catch(CartWithTheIdNotPresentException e) {
			
			responseEntity = new ResponseEntity<String>("Cart with ID not found", HttpStatus.NOT_FOUND);
		}
		
		return responseEntity;
	}
	
	 /*
     *  Handler method to add get a cart by id. This handler method should map to
     * the URL "/cart/{cartId}". This method will return the retreived cart in the response body
     *  with the Httpstatus OK, if the cart is found, else, returns the message
     * "Cart Not found" with the status NOT_FOUND.
     */
	
	
	@PutMapping(value="/cart/{cartId}")
	public ResponseEntity<?> updateCartHandler(@PathVariable("cartId") int id, @RequestBody Cart cart){
		
		ResponseEntity<?> responseEntity;
		
		try {
			cart.setCartId(id);
			Cart updateCart = cartService.updateCart(cart);
			responseEntity = new ResponseEntity<Cart>(updateCart, HttpStatus.OK);
		}catch(CartWithTheIdNotPresentException e) {
			
			responseEntity = new ResponseEntity<String>("Cart with ID not found", HttpStatus.NOT_FOUND);
		}
		
		return responseEntity;
	}
	
	@DeleteMapping(value="/cart/{cartId}")
	public ResponseEntity<?> deleteCartHandler(@PathVariable("cartId") int id){
		ResponseEntity<?> responseEntity;
		
		try {
			Cart deleteCart = cartService.deleteCart(id);
			responseEntity = new ResponseEntity<Cart>(deleteCart, HttpStatus.OK);
		}catch(CartWithTheIdNotPresentException e) {
			responseEntity = new ResponseEntity<String>("Cart with this ID is not found", HttpStatus.NOT_FOUND);
		}
		
		
		return responseEntity;
	}

}      