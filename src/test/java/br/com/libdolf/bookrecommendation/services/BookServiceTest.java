package br.com.libdolf.bookrecommendation.services;

import br.com.libdolf.bookrecommendation.connections.GoogleBooksApiConnection;
import br.com.libdolf.bookrecommendation.controllers.dtos.BookResponse;
import br.com.libdolf.bookrecommendation.exceptions.NotFoundException;
import br.com.libdolf.bookrecommendation.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.com.libdolf.bookrecommendation.utils.BookResponseMock.buildValidBookResponse;
import static br.com.libdolf.bookrecommendation.utils.BookResponseMock.buildValidBookResponseList;
import static br.com.libdolf.bookrecommendation.utils.GBResponseMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private GoogleBooksApiConnection connection;

    @Mock
    private BookMapper mockMapper;

    @Test
    public void testGetById_Successful() {
        var mockResponse = oneElement();
        when(connection.getBookById(anyString())).thenReturn(mockResponse.getItems().getFirst());

        var expectedResponse = buildValidBookResponse();
        when(mockMapper.mapToBookResponse(any())).thenReturn(expectedResponse);

        var actualResponse = bookService.getById("11h7DwAAQBAJ");

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testGetById_BookNotFound() {
        when(connection.getBookById(anyString())).thenReturn(null);

        assertThrows(NotFoundException.class, () -> bookService.getById("nonExistentId"));
    }

    @Test
    public void testGetByTitle_Successful() {
        var mockResponse = twoElements();
        when(connection.getBookByTitle(anyString())).thenReturn(mockResponse);

        var expectedResponses = buildValidBookResponseList();
        when(mockMapper.mapToBookResponseList(anyList())).thenReturn(expectedResponses);

        var actualResponses = bookService.getByTitle("validTitle");

        assertEquals(expectedResponses, actualResponses);
    }

    @Test
    public void testGetByTitle_BookNotFound() {
        var mockResponse = zeroElements();
        when(connection.getBookByTitle(anyString())).thenReturn(mockResponse);

        assertThrows(NotFoundException.class, () -> bookService.getByTitle("nonExistentTitle"));
    }



}