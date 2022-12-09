package com.cgi.main.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.main.Exceptions.BookWithTheIDAlreadyExistException;
import com.cgi.main.Exceptions.BookWithTheIDDoesNotExistException;
import com.cgi.main.Model.Book;
import com.cgi.main.Repository.BookRepository;


@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;


    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    @Override
    public Book getBookById(Long id) throws BookWithTheIDDoesNotExistException {
        Optional<Book> optional = bookRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new BookWithTheIDDoesNotExistException();
    }

    @Override
    public Book addBook(Book book) throws BookWithTheIDAlreadyExistException {
        Optional<Book> optional = bookRepository.findByIdAndTitle(book.getId(), book.getTitle());
        if(optional.isEmpty()){
            bookRepository.save(book);
            return book;
        }
        throw new BookWithTheIDAlreadyExistException();
    }
    

    @Override
    public void deleteBookById(Long id) throws BookWithTheIDDoesNotExistException {
        Optional<Book> optional = bookRepository.findById(id);
        if(optional.isPresent()){
            bookRepository.deleteById(id);
        } else {
        throw new BookWithTheIDDoesNotExistException();
        }
    }

    @Override
    public Book updateBook(Book book)throws BookWithTheIDAlreadyExistException {
        Optional<Book> optional = bookRepository.findById(book.getId());
        if(optional.isPresent()){
            bookRepository.save(book);
            return optional.get();
        }
        throw new BookWithTheIDAlreadyExistException();
    }


}