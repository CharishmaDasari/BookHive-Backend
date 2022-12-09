package com.example.demo.controller;

import java.util.List;


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Order;
import com.example.demo.service.OrderService;

import exceptions.OrderWithIDExists;
import exceptions.OrderWithIDNotFound;

@CrossOrigin("*")
@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getAllOrdersHandler()
	{
		ResponseEntity<List<Order>> responseEntity;
		List<Order> orders = orderService.getAllOrders();
		responseEntity = new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/orders/{orderId}")
	public ResponseEntity<?> getOrderByIdHandler(@PathVariable("orderId") int id)
	{
		ResponseEntity<?> responseEntity;
		try
		{
			Order order = orderService.getOrderByID(id);
			responseEntity = new ResponseEntity<Order>(order, HttpStatus.OK);
		}
		catch(OrderWithIDNotFound e)
		{
			responseEntity = new ResponseEntity<String>("Order Not Found", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	@PostMapping("/orders")
	public ResponseEntity<?> addNewOrderHandler(@RequestBody Order newOrder)
	{
		ResponseEntity<?> responseEntity;
		try
		{
			Order order = orderService.addNewOrder(newOrder);
			responseEntity = new ResponseEntity<Order>(order, HttpStatus.CREATED);
		}
		catch(OrderWithIDExists e)
		{
			responseEntity = new ResponseEntity<String>("Duplicate Resource", HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	
	@PutMapping("/orders/{orderId}")
	public ResponseEntity<?> updateOrderHandler(@PathVariable("orderId") int id, @RequestBody Order updatedOrder)
	{
		ResponseEntity<?> responseEntity;
		try
		{
			Order order = orderService.editOrderByID(updatedOrder);
			responseEntity = new ResponseEntity<Order>(order, HttpStatus.OK);
		}
		catch(OrderWithIDNotFound e)
		{
			responseEntity = new ResponseEntity<String>("Order Not Found", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	@DeleteMapping("/orders/{orderId}")
	public ResponseEntity<String> deleteOrderHandler(@PathVariable("orderId") int id)
	{
		ResponseEntity<String> responseEntity;
		try
		{
			orderService.deleteOrderByID(id);
			responseEntity = new ResponseEntity<String>("Order Deleted", HttpStatus.OK);
		}
		catch(OrderWithIDNotFound e)
		{
			responseEntity = new ResponseEntity<String>("Order Not Found", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
    @GetMapping("/orders/status")
	public ResponseEntity<?> getAllOrdersByDeliveryStatusHandler(@RequestParam("status") String deliveryStatus)
	{
    	ResponseEntity<?> responseEntity;
		List<Order> orders = orderService.getOrdersByDeliveryStatus(deliveryStatus);
		responseEntity = new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
		return responseEntity;
	}
    
    @GetMapping("/orders/user")
    public ResponseEntity<?> getAllOrderByUserId(@RequestParam("userId") Integer userId)
    {
    	ResponseEntity<?> responseEntity;
		List<Order> orders = orderService.getOrderByUserId(userId);
		responseEntity = new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
		return responseEntity;
    }
}
