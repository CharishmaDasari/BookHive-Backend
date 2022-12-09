package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.models.Order;
import com.example.demo.models.Book;

@SpringBootTest
public class OrderRepositoryTest {

	@Autowired
	OrderRepository orderRepository;
	
	 @Test
	 public void testAddOrder()
	 {
		 Book book = new Book(101, "xyz", 3456.90f, 1);
		 List<Book> books=new ArrayList<>();
		 books.add(book);
		 Order order = new Order(101, 551, "pune", "booked",books);
		 Order addedOrder=orderRepository.save(order);
		 assertEquals(order.getUserId(),addedOrder.getUserId(),"Order with userid already exist");
	 }
	 @Test
	 public void givenInValidUserIdThenReturnEmptyOptional()
	 {
		 assertTrue(orderRepository.findById(20).isEmpty());
	 }
	 @Test
	 public void givenValidUserIdThenReturn()
	 {
		 Book book = new Book(101, "jeans", 3456.90f, 1);
		 List<Book> books=new ArrayList<>();
		 books.add(book);
		 Order order = new Order(101, 551, "pune", "booked",books);
		 orderRepository.save(order);
		 assertTrue(orderRepository.findById(101).isPresent());
	 }
}
