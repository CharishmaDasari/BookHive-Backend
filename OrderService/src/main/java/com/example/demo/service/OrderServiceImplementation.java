package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Order;
import com.example.demo.repository.OrderRepository;

import exceptions.OrderWithIDExists;
import exceptions.OrderWithIDNotFound;

@Service
public class OrderServiceImplementation implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order getOrderByID(int orderId) throws OrderWithIDNotFound {
		Optional<Order> orderOptional = orderRepository.findById(orderId);
		if(orderOptional.isPresent())
		{
			return orderOptional.get();
		}
		else
		{
			throw new OrderWithIDNotFound();
		}
	}

	@Override
	public Order addNewOrder(Order newOrder) throws OrderWithIDExists {
		Optional<Order> orderOptional = orderRepository.findById(newOrder.getOrderNo());
		if(orderOptional.isEmpty())
		{
			orderRepository.save(newOrder);
			return newOrder;
		}
		else
		{
			throw new OrderWithIDExists();
		}
	}

	@Override
	public Order editOrderByID(Order updatedOrder) throws OrderWithIDNotFound {
		Optional<Order> orderOptional = orderRepository.findById(updatedOrder.getOrderNo());
		if(orderOptional.isPresent())
		{
			orderRepository.save(updatedOrder);
			return orderOptional.get();
		}
		else
		{
			throw new OrderWithIDNotFound();
		}
	}

	@Override
	public void deleteOrderByID(int orderId) throws OrderWithIDNotFound {
		Optional<Order> orderOptional = orderRepository.findById(orderId);
		if(orderOptional.isPresent())
		{
			orderRepository.delete(orderOptional.get());
		}
		else
		{
			throw new OrderWithIDNotFound();
		}
		
	}

	@Override
	public List<Order> getOrdersByDeliveryStatus(String deliveryStatus) {
		return orderRepository.findByDeliveryStatus(deliveryStatus);
	}

	@Override
	public List<Order> getOrderByUserId(int userId) {
		return orderRepository.findByUserId(userId);
	}

}
