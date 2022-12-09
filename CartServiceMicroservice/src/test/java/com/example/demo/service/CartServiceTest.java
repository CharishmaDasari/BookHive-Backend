package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.CartService.exceptions.CartWithTheIDAlreadyPresentException;
import com.example.CartService.exceptions.CartWithTheIdNotPresentException;
import com.example.CartService.model.Book;
import com.example.CartService.model.Cart;
import com.example.CartService.repository.CartRepository;
import com.example.CartService.service.CartServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

	@Mock
	CartRepository cartRepository;
	@InjectMocks
	CartServiceImpl service;
	@Test
	public void givenValidCartIDThenShouldReturn() throws CartWithTheIdNotPresentException 
	{
		Book book = new Book(101, "jeans", 3456.90f, 1);
		List<Book> books=new ArrayList<>();
		books.add(book);
		Cart cart = new Cart(1, 551, books);
		Optional<Cart> optionalcart=Optional.of(cart);
		when(cartRepository.findById(1)).thenReturn(optionalcart);
		Cart retrcart=service.getCartById(1);
		assertEquals(cart.getCartId(),retrcart.getCartId(),"should return cart for valid id of exixting cart");
	}
	
	public void givenValidCartIDThenShouldThrowException()
	{
		Optional<Cart> optionalcart=Optional.empty();
		when(cartRepository.findById(1)).thenReturn(optionalcart);
		assertThrows(CartWithTheIDAlreadyPresentException.class,()->{
			service.getCartById(1);
		});
	}
	
}
