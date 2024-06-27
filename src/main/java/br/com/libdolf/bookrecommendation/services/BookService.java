package br.com.libdolf.bookrecommendation.services;

import br.com.libdolf.bookrecommendation.connections.GoogleBooksApiConnection;
import br.com.libdolf.bookrecommendation.controllers.dtos.BookResponse;
import br.com.libdolf.bookrecommendation.exceptions.NotFoundException;
import br.com.libdolf.bookrecommendation.mapper.BookMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final GoogleBooksApiConnection connection;
    private final BookMapper mapper;

    public BookService(GoogleBooksApiConnection connection, BookMapper mapper) {
        this.connection = connection;
        this.mapper = mapper;
    }


    public BookResponse getById(String id) {
        var response = connection.getBookById(id);

        if (response == null) {
            throw new NotFoundException("book not found");
        }

        return mapper.mapToBookResponse(response);
    }

    public List<BookResponse> getByTitle(String title) {
        var response = connection.getBookByTitle(title);
        if (response.getTotalItems() == 0){
            throw new NotFoundException("book not found");
        }

        return mapper.mapToBookResponseList(response.getItems());
    }
}
