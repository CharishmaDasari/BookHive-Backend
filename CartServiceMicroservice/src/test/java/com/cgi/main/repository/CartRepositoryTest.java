package com.cgi.main.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.CartService.model.Book;
import com.example.CartService.model.Cart;

import com.example.CartService.repository.CartRepository;

@SpringBootTest
public class CartRepositoryTest {

	@Autowired
	CartRepository cartRepository;
	@Test
	public void testAddCart()
	{
		Book book = new Book(101, "jeans", 3456.90f, 1);
		List<Book> books=new ArrayList<>();
		books.add(book);
		Cart cart = new Cart(1, 551, books);
		Cart addedCart=cartRepository.save(cart);
		assertEquals(cart.getUserId(), addedCart.getUserId(),"Cart already exist");
	}

	@Test
	public void givenInValidCartIdThenReturnEmptyOptional()
	{
		assertTrue(cartRepository.findById(20).isEmpty());
	}

	@Test
	public void givenValidCartIdThenReturn()
	{
		Book book = new Book(101, "jeans", 3456.90f, 1);
		List<Book> books=new ArrayList<>();
		books.add(book);
		Cart cart = new Cart(1, 551, books);
		cartRepository.save(cart);
		assertTrue(cartRepository.findById(1).isPresent());
	}

}
