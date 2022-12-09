package com.cgi.main.Services;

import java.util.List;

import com.cgi.main.Exceptions.BookWithTheIDAlreadyExistException;
import com.cgi.main.Exceptions.BookWithTheIDDoesNotExistException;
import com.cgi.main.Model.Book;

public interface BookService {
    Book getBookById(Long id) throws BookWithTheIDDoesNotExistException;
    
    void deleteBookById(Long id) throws BookWithTheIDDoesNotExistException;
    Book updateBook(Book book) throws BookWithTheIDAlreadyExistException;
	Book addBook(Book book) throws BookWithTheIDAlreadyExistException;
	List<Book> getAllBooks();

}


