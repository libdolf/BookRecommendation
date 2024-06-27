package br.com.libdolf.bookrecommendation.controllers.dtos;

import java.util.List;

public record BookResponse(
        String bookId,
        String title,
        List<String> authors,
        String publisher,
        String publishedDate,
        String description,
        Integer pageCount,
        List<String> categories
) { }
