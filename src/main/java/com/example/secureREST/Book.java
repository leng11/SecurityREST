package com.example.secureREST;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Book {
	private int id;
	private String name;
	private String author;

}
