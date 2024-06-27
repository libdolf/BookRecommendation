package br.com.libdolf.bookrecommendation.services;

import br.com.libdolf.bookrecommendation.connections.GoogleBooksApiConnection;
import br.com.libdolf.bookrecommendation.controllers.dtos.BookResponse;
import br.com.libdolf.bookrecommendation.exceptions.NotFoundException;
import br.com.libdolf.bookrecommendation.mapper.BookMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {
    private final GoogleBooksApiConnection connection;
    private final BookMapper mapper;

    public RecommendationService(GoogleBooksApiConnection connection, BookMapper mapper) {
        this.connection = connection;
        this.mapper = mapper;
    }


    public List<BookResponse> getRecommendations(String bookId) {
        var bookById = connection.getBookById(bookId);
        if (bookById == null) {
            throw new NotFoundException("book not found");
        }
        var authors = bookById.getVolumeInfo().getAuthors();
        var categories = bookById.getVolumeInfo().getCategories();

        var recommendations = connection.getRecommendations(authors, categories);
        if (recommendations.getTotalItems() == 0){
            throw new NotFoundException("recommendation not found");
        }
        return mapper.mapToBookResponseList(recommendations.getItems());
    }
}
