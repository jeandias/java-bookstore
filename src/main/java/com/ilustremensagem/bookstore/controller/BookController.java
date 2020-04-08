package com.ilustremensagem.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

import com.ilustremensagem.bookstore.exception.ResourceNotFoundException;
import com.ilustremensagem.bookstore.model.Book;
import com.ilustremensagem.bookstore.repository.AuthorRepository;
import com.ilustremensagem.bookstore.repository.BookRepository;

@RestController
class BookController {
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@GetMapping("/books")
	public Page<Book> getBooks(Pageable pageable) {
		return bookRepository.findAll(pageable);
	}

	@GetMapping("/authors/{authorId}/books")
	public List<Book> getBooksByAuthorId(@PathVariable Long authorId) {
		return bookRepository.findByAuthorId(authorId);
	}

	@PostMapping("/authors/{authorId}/books")
	public Book addBook(@PathVariable Long authorId, @Valid @RequestBody Book book) {
		return authorRepository.findById(authorId).map(author -> {
			book.setAuthor(author);
			return bookRepository.save(book);
		}).orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + authorId));
	}

	@PutMapping("/authors/{authorId}/books/{bookId}")
	public Book updateBook(@PathVariable Long authorId, @PathVariable Long bookId,
			@Valid @RequestBody Book bookRequest) {
		if (!authorRepository.existsById(authorId)) {
			throw new ResourceNotFoundException("Author not found with id " + authorId);
		}

		return bookRepository.findById(bookId).map(book -> {
			book.setDescription(bookRequest.getDescription());
			return bookRepository.save(book);
		}).orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));
	}

	@DeleteMapping("/authors/{authorId}/books/{bookId}")
	public ResponseEntity<?> deleteBook(@PathVariable Long authorId, @PathVariable Long bookId) {
		if (!authorRepository.existsById(authorId)) {
			throw new ResourceNotFoundException("Author not found with id " + authorId);
		}

		return bookRepository.findById(bookId).map(book -> {
			bookRepository.delete(book);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));

	}
}
