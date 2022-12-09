package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection="Order")
public class Order {

	@Id
    private int orderNo;
	private int userId;
	private String deliveryAddress;
	private String deliveryStatus;
	private List<Book> books;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(int orderNo, int userId, String deliveryAddress, String deliveryStatus, List<Book> books) {
		super();
		this.orderNo = orderNo;
		this.userId = userId;
		this.deliveryAddress = deliveryAddress;
		this.deliveryStatus = deliveryStatus;
		this.books = books;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Order [orderNo=" + orderNo + ", userId=" + userId + ", deliveryAddress=" + deliveryAddress
				+ ", deliveryStatus=" + deliveryStatus + ", books=" + books + "]";
	}
	
	
}