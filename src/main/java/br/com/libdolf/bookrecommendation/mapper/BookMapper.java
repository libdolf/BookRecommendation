package br.com.libdolf.bookrecommendation.mapper;

import br.com.libdolf.bookrecommendation.connections.dtos.GoogleBooksResponse;
import br.com.libdolf.bookrecommendation.controllers.dtos.BookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class BookMapper {
    @Mapping(source = "id", target = "bookId")
    @Mapping(source = "volumeInfo.title", target = "title")
    @Mapping(source = "volumeInfo.authors", target = "authors")
    @Mapping(source = "volumeInfo.publisher", target = "publisher")
    @Mapping(source = "volumeInfo.publishedDate", target = "publishedDate")
    @Mapping(source = "volumeInfo.description", target = "description")
    @Mapping(source = "volumeInfo.pageCount", target = "pageCount")
    @Mapping(source = "volumeInfo.categories", target = "categories")
    public abstract BookResponse mapToBookResponse(GoogleBooksResponse.Item googleBooksResponse);

    public abstract List<BookResponse> mapToBookResponseList(List<GoogleBooksResponse.Item> items);
}
