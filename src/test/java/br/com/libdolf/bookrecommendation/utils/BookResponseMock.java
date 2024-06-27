package br.com.libdolf.bookrecommendation.utils;

import br.com.libdolf.bookrecommendation.controllers.dtos.BookResponse;

import java.util.List;

public class BookResponseMock {
    public static BookResponse buildValidBookResponse() {
        return new BookResponse(
                "11h7DwAAQBAJ",
                "Harry Potter e a Criança Amaldiçoada - Partes Um e Dois",
                List.of("J.K. Rowling", "John Tiffany", "Jack Thorne"),
                "Pottermore Publishing",
                "2018-09-19",
                "Description",
                437,
                List.of("Drama")
        );
    }

    public static List<BookResponse> buildValidBookResponseList() {
        return List.of(buildValidBookResponse(), buildValidBookResponse());
    }
}
