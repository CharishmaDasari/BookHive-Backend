package com.example.CartService.service;

import com.example.CartService.model.Cart;
import com.example.CartService.exceptions.CartWithTheIDAlreadyPresentException;
import com.example.CartService.exceptions.CartWithTheIdNotPresentException;



public interface CartService {
	
	
	Cart getCartById(int id) throws CartWithTheIdNotPresentException;
	Cart createNewCart(Cart cart) throws CartWithTheIDAlreadyPresentException;
	Cart deleteCart(int id) throws CartWithTheIdNotPresentException;
	Cart updateCart(Cart cart) throws CartWithTheIdNotPresentException;
}
