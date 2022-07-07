package com.example.secureREST;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class BookService {
	private static AtomicInteger idCounter = new AtomicInteger();
	private Map<String, Book> books = new ConcurrentHashMap<>(128);
	
	public Book add(Book book) {
		Book savedBook = Book.builder().
								id(idCounter.getAndIncrement()).
								name(book.getName()).
								author(book.getAuthor()).
								build();
				
		books.put(book.getName(), savedBook);
		return savedBook;
	}
	
	public int delete(String name) {
		return books.remove(name)==null?0:1;
	}
	
	public List<Book> list() {
		return books.values().stream().collect(Collectors.toList());
	}

}
