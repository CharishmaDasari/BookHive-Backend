package com.example.CartService.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/** Annotate the class with @Document annotation */

@Document(collection = "Cart")
public class Cart {
	
/*  id is annotated with @Id to make it as primary key */	
	
	
	
	@Id
	private int cartId;
	private int userId;
	private List<Book> books;
	
	
	public Cart() {}
	
/* parameterized constructor */	
	
	public Cart(int cartId, int userId, List<Book> books) {
		this.cartId = cartId;
		this.userId = userId;
		this.books = books;
	}

/* getter and setter methods for all the properties */
	
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}


	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", userId=" + userId + ", books=" + books + "]";
	}
}
