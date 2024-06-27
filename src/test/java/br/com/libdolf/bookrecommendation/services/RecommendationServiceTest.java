package br.com.libdolf.bookrecommendation.services;


import br.com.libdolf.bookrecommendation.connections.GoogleBooksApiConnection;
import br.com.libdolf.bookrecommendation.exceptions.NotFoundException;
import br.com.libdolf.bookrecommendation.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.com.libdolf.bookrecommendation.utils.BookResponseMock.buildValidBookResponseList;
import static br.com.libdolf.bookrecommendation.utils.GBResponseMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class RecommendationServiceTest {
    @InjectMocks
    private RecommendationService recommendationService;
    @Mock
    private GoogleBooksApiConnection connection;
    @Mock
    private BookMapper mapper;

    @Test
    public void testGetRecommendations_Successful() {
        var mockResponse = twoElements();
        when(connection.getBookById(any())).thenReturn(oneElement().getItems().getFirst());
        when(connection.getRecommendations(any(), any())).thenReturn(mockResponse);

        var expectedResponse = buildValidBookResponseList();
        when(mapper.mapToBookResponseList(anyList())).thenReturn(expectedResponse);

        var actualResponse = recommendationService.getRecommendations("Valid id");

        assertEquals(actualResponse, expectedResponse);
    }

    @Test
    public void testGetRecommendations_BookNotFoundException() {
        when(connection.getBookById(any())).thenReturn(null);

        assertThrows(NotFoundException.class, () -> recommendationService.getRecommendations("nonExistentId"));
    }

    @Test
    public void testGetRecommendations_RecommendationNotFoundException() {
        when(connection.getBookById(any())).thenReturn(oneElement().getItems().getFirst());
        when(connection.getRecommendations(any(), any())).thenReturn(zeroElements());

        assertThrows(NotFoundException.class, () -> recommendationService.getRecommendations("Valid id"));
    }

}