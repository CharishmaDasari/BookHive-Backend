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

import com.example.demo.models.Order;
import com.example.demo.models.Book;
import com.example.demo.repository.OrderRepository;

import exceptions.OrderWithIDNotFound;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

	@Mock
	OrderRepository orderRepository;
	@InjectMocks
	OrderServiceImplementation service;

	@Test
	public void givenValidOrderIDThenShouldReturnOrder() throws OrderWithIDNotFound 
	{
		Book book = new Book(101, "jeans", 3456.90f, 1);
		List<Book> books=new ArrayList<>();
		books.add(book);
		Order order = new Order(101, 551, "pune", "booked",books);
		Optional<Order> optionalProd = Optional.of(order);
		when(orderRepository.findById(101)).thenReturn(optionalProd);
		Order retorder=service.getOrderByID(101);
		assertEquals(order.getUserId(), retorder.getUserId(),"should return Order for valid id of exixting Order");
	}

	@Test
	public void givenInValidProductIDThenShouldThrowException() throws OrderWithIDNotFound
	{
		Optional<Order> optionalProd =Optional.empty();
		when(orderRepository.findById(101)).thenReturn(optionalProd);
		assertThrows(OrderWithIDNotFound.class,()->{
			service.getOrderByID(101);
		});
	}
}
