package br.com.libdolf.bookrecommendation.controllers;

import br.com.libdolf.bookrecommendation.controllers.dtos.BookResponse;
import br.com.libdolf.bookrecommendation.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> searchBookById(@PathVariable String id) {
        return ResponseEntity.ok(bookService.getById(id));
    }

    //TODO: implement pagination
    @GetMapping
    public ResponseEntity<List<BookResponse>> searchBooksByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookService.getByTitle(title));
    }
}
