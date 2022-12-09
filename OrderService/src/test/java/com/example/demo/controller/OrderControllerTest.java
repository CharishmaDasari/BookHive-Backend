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
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.example.demo.models.Order;
import com.example.demo.models.Book;
import com.example.demo.service.OrderServiceImplementation;

public class OrderControllerTest {

	@Autowired
	private transient MockMvc mockMvc;
	@MockBean
	private transient OrderServiceImplementation implementation;
	
	private transient Order order;
	@InjectMocks
	private OrderController controller;
	
	@BeforeEach
	public void setUp()
	{
		Book book = new Book(101, "", 3456.90f, 1);
		List<Book> books=new ArrayList<>();
		books.add(book);
		Order order = new Order(101, 551, "pune", "booked",books);
	}
	

}
