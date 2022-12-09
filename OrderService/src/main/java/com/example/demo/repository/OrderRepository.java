package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, Integer> {

	List<Order> findByDeliveryStatus(String deliveryStatus);
	List<Order> findByUserId(Integer userId);
}
