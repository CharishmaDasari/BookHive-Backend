package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.example.CartService.model.Book;
import com.example.CartService.model.Cart;

import com.example.CartService.service.CartServiceImpl;

public class CartControllerTest {

	@Autowired
	private transient MockMvc mockMvc;
	
	@MockBean
	private transient CartServiceImpl cartServiceImpl;
	
	private transient Cart cart;
	
	@BeforeEach
	public void setUp()
	{
		Book book = new Book(101, "jeans", 3456.90f, 1);
		List<Book> books=new ArrayList<>();
		books.add(book);
		Cart cart = new Cart(1, 551, books);
	}
	@Test
	public void testCart() throws Exception
	{
		when(cartServiceImpl.createNewCart(any())).thenReturn(cart);
		((ResultActions) ((MockHttpServletRequestBuilder) mockMvc.perform(post("/api/v1/cart"))).contentType(MediaType.APPLICATION_JSON)
		.content("{\"id\":1,\"userId\":\"551\",\"books\":\"book\"}"))
.andExpect(status().isCreated())
.andExpect(jsonPath("$.id").value(1));
	}
}
