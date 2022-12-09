package com.example.CartService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.CartService.model.Cart;
/** This class should be annotated with @repository */
@Repository
public interface CartRepository extends MongoRepository<Cart, Integer>{

}
