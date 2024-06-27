package br.com.libdolf.bookrecommendation.services;

import br.com.libdolf.bookrecommendation.connections.GoogleBooksApiConnection;
import br.com.libdolf.bookrecommendation.controllers.dtos.BookResponse;
import br.com.libdolf.bookrecommendation.exceptions.NotFoundException;
import br.com.libdolf.bookrecommendation.mapper.BookMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {
    private final GoogleBooksApiConnection googleBooksConnection;
    private final BookMapper mapper;

    public RecommendationService(GoogleBooksApiConnection googleBooksConnection, BookMapper mapper) {
        this.googleBooksConnection = googleBooksConnection;
        this.mapper = mapper;
    }


    public List<BookResponse> getRecommendations(String bookId) {
        var bookById = googleBooksConnection.getBookById(bookId);
        if (bookById == null) {
            throw new NotFoundException("book not found");
        }
        var authors = bookById.getVolumeInfo().getAuthors();
        var categories = bookById.getVolumeInfo().getCategories();

        var recommendations = googleBooksConnection.getRecommendations(authors, categories);
        if (recommendations.getTotalItems() == 0){
            throw new NotFoundException("recommendation not found");
        }
        return mapper.mapToBookResponseList(recommendations.getItems());
    }
}
