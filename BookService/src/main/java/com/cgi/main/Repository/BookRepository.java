package com.cgi.main.Repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.main.Model.Book;

@Repository
	@Transactional
	public interface BookRepository extends JpaRepository<Book, Long> {
	Optional<Book> findByIdAndTitle(Long id, String title);
	}




