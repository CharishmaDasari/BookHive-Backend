package com.example.CartService.model;

public class Book {

	private int bookId;
	private String title;
	private float price;
	private int quantity;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Book(int bookId, String title, float price, int quantity) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.price = price;
		this.quantity = quantity;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", price=" + price + ", quantity=" + quantity
				+ "]";
	}
	
}