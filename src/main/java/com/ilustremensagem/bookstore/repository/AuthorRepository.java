package com.ilustremensagem.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ilustremensagem.bookstore.model.Author;

@RepositoryRestResource
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
