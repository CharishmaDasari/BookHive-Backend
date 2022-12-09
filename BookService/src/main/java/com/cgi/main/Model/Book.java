package com.cgi.main.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

	    
	  //To make id field as primarykey
	    @Id 
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    @Column(nullable = false, length = 240, unique = true)
	    private String title;

	    @Column(nullable = false, length = 240)
	    private String description;

	    @Column(nullable = false, length = 240)
	    private Double price;

	    @Column(nullable = true, length = 240)
	    private String url;

	    //default constructor
	    public Book(){}

	    //Parameterized constructor
	    public Book(Long id, String title, String description, Double price, String url) {
	        this.id = id;
	        this.title = title;
	        this.description = description;
	        this.price = price;
	        this.url = url;
	    }
	    
	    /* Getters and Setters methods */

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public Double getPrice() {
	        return price;
	    }

	    public void setPrice(Double price) {
	        this.price = price;
	    }

	    public String getUrl() {
	        return url;
	    }

	    public void setUrl(String url) {
	        this.url = url;
	    }
	}
