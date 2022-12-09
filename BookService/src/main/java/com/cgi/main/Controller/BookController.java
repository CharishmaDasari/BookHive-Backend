package com.cgi.main.Controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.main.Exceptions.BookWithTheIDAlreadyExistException;
import com.cgi.main.Exceptions.BookWithTheIDDoesNotExistException;
import com.cgi.main.Model.Book;
import com.cgi.main.Services.BookService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class BookController {
	 @Autowired
	    private BookService bookService;
	    
	    //to get all the products with http response
	    @GetMapping(value = "/book")
	    public ResponseEntity<List<Book>> getAllBooks(){
	        ResponseEntity<List<Book>> responseEntity;

	        List<Book> bookList = bookService.getAllBooks();
	        responseEntity = new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
	        return responseEntity;
	    }
	    
	    //controller method for adding product with http response
	    @PostMapping("/book")
	    public ResponseEntity<?> addBook(@RequestBody Book book) {
	        ResponseEntity<?> responseEntity;
	        try {
	            Book newBook = bookService.addBook(book);
	            responseEntity = new ResponseEntity<Book>(newBook, HttpStatus.CREATED);
	        } catch (BookWithTheIDAlreadyExistException e){
	            responseEntity = new ResponseEntity<String>("Failed to store the Book", HttpStatus.CONFLICT);
	        }
	        return responseEntity;
	    }

	  //controller method for getting product with id with http response
	    @GetMapping("book/{BookId}")
	    public ResponseEntity<?> getBookById(@PathVariable("BookId") Long id) {
	        ResponseEntity<?> responseEntity;
	        try {
	            Book book = bookService.getBookById(id);
	            responseEntity = new ResponseEntity<Book>(book, HttpStatus.OK);
	        } catch (BookWithTheIDDoesNotExistException e) {
	            responseEntity = new ResponseEntity<String>("Failed to store the Book", HttpStatus.CONFLICT);
	        }
	        return responseEntity;
	    }

	     
	    //controller method for adding product with http response
	    @PutMapping("book/{bookId}")
	    public ResponseEntity<?> updateProduct(@RequestBody Book book, @PathVariable ("bookId") Long id){
	        ResponseEntity<?> responseEntity;
	        try {
	            book.setId(id);
	            Book newBook = bookService.updateBook(book);
	            responseEntity = new ResponseEntity<Book>(newBook, HttpStatus.OK);
	        } catch (BookWithTheIDAlreadyExistException e) {
	            responseEntity = new ResponseEntity<String>("Failed to update the Book", HttpStatus.CONFLICT);
	        }
	        return responseEntity;
	    }
	    
	  //controller method for deleting product with http response
	    @DeleteMapping( "book/{bookId}")
	    public ResponseEntity<String> deleteBook(@PathVariable("bookId") Long id){

	            ResponseEntity<String> responseEntity;
	            try {
	                bookService.deleteBookById(id);
	                responseEntity = new ResponseEntity<String>("Book Deleted", HttpStatus.OK);
	            } catch(BookWithTheIDDoesNotExistException e) {
	                responseEntity = new ResponseEntity<String>("Book Not Found", HttpStatus.NOT_FOUND);
	            }
	            return responseEntity;
	        }




	}
