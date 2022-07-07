package com.example.secureREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SampleRestController {
	public static final String ADD_BOOK_URL = "/v1/books/add";
	public static final String DELETE_BOOK_BY_NAME_URL = "/v1/books/delete/{name}";
	public static final String LIST_BOOK_URL = "/v1/books/list";
	
	@Autowired
	BookService service;
	
	@PostMapping(ADD_BOOK_URL)
	public Book add(@RequestBody Book book) {
			return service.add(book);
	}
	
	@DeleteMapping(DELETE_BOOK_BY_NAME_URL)
	public int delete(@PathVariable final String name) {
		return service.delete(name);
	}
	
	@GetMapping(LIST_BOOK_URL)
	public Book[] list() {
		return service.list().toArray(new Book[0]);
	}
}
