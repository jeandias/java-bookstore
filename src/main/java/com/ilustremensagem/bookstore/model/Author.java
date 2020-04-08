package com.ilustremensagem.bookstore.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "authors")
public class Author extends AuditModel {
	@Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
