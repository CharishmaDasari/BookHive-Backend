package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Order;

import exceptions.OrderWithIDExists;
import exceptions.OrderWithIDNotFound;


public interface OrderService {

	List<Order> getAllOrders();
	Order getOrderByID(int orderId) throws OrderWithIDNotFound;
	Order addNewOrder(Order newOrder) throws OrderWithIDExists;
	Order editOrderByID(Order updatedOrder) throws OrderWithIDNotFound;
	void deleteOrderByID(int orderId) throws OrderWithIDNotFound;
	
	List<Order> getOrdersByDeliveryStatus(String deliveryStatus);
	List<Order> getOrderByUserId(int userId);
}
