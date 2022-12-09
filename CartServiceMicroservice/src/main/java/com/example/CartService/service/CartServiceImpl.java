package com.example.CartService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CartService.model.Cart;
import com.example.CartService.repository.CartRepository;
import com.example.CartService.exceptions.CartWithTheIDAlreadyPresentException;
import com.example.CartService.exceptions.CartWithTheIdNotPresentException;

/** Annotate the class with @Service Annotation */
@Service
public class CartServiceImpl implements CartService {

	/** Constructor based Autowiring should be implemented */
	
	@Autowired
	private CartRepository cartRepository;
	
	
/*  This is method is used to get all the Carts*/	
	
	@Override
	public Cart getCartById(int id) throws CartWithTheIdNotPresentException {
		Optional<Cart> optional = cartRepository.findById(id);
		
		if(optional.isPresent()) {
			
			return optional.get();
		}
		throw new CartWithTheIdNotPresentException();
	}

	@Override
	public Cart createNewCart(Cart cart) throws CartWithTheIDAlreadyPresentException {
		Optional<Cart> optional = cartRepository.findById(cart.getCartId());
		
		if(optional.isEmpty()) {
			cartRepository.save(cart);
			return cart;
		}
		throw new CartWithTheIDAlreadyPresentException();
	}

/* This method is used to get a cart by id. The method should throw
CartWithTheIdNotPresentException, if the cart with the given id is not found
*/	
	
	
	@Override
	public Cart deleteCart(int id) throws CartWithTheIdNotPresentException {
		Optional<Cart> optional = cartRepository.findById(id);
		
		if(optional.isPresent()) {
			cartRepository.delete(optional.get());
			return optional.get();
		} else {
		throw new CartWithTheIdNotPresentException();
		}
	}

	/*
	 * This method is used to update a new cart. The method should throw
	 * CartWithTheIdNotPresentException, if the new cart that we are trying update is
	 * already saved
	 */
		
	
	@Override
	public Cart updateCart(Cart cart) throws CartWithTheIdNotPresentException {
		Optional<Cart> optional = cartRepository.findById(cart.getCartId());
		
		if(optional.isPresent()) {
			cartRepository.save(cart);
			return cart;
		}
		throw new CartWithTheIdNotPresentException();
	}
}

