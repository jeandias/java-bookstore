package com.ilustremensagem.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

import com.ilustremensagem.bookstore.model.Book;

@RepositoryRestResource
public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByAuthorId(Long authorId);
}
