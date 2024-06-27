package br.com.libdolf.bookrecommendation.connections;

import br.com.libdolf.bookrecommendation.connections.dtos.GoogleBooksResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static br.com.libdolf.bookrecommendation.utils.GBResponseMock.oneElement;
import static br.com.libdolf.bookrecommendation.utils.GBResponseMock.twoElements;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class GoogleBooksApiConnectionTest {
    @InjectMocks
    private GoogleBooksApiConnection connection;
    @Mock
    private RestTemplate restTemplate;

    @Test
    void testGetBooksByTitle() {
        var expectedResponse = twoElements();
        when(restTemplate.getForObject(anyString(), eq(GoogleBooksResponse.class)))
                .thenReturn(expectedResponse);

        var actualResponse = connection.getBookByTitle("Harry Potter e a Criança Amaldiçoada - Partes Um e Dois");

        assertEquals(expectedResponse, actualResponse);
        verify(restTemplate, times(1)).getForObject("https://www.googleapis.com/books/v1/volumes?q=Harry Potter e a Criança Amaldiçoada - Partes Um e Dois", GoogleBooksResponse.class);
    }

    @Test
    void testGetBookById() {
        var expectedResponse = oneElement().getItems().getFirst();
        when(restTemplate.getForObject(anyString(), eq(GoogleBooksResponse.Item.class)))
                .thenReturn(expectedResponse);

        var actualResponse = connection.getBookById("11h7DwAAQBAJ");

        assertEquals(expectedResponse, actualResponse);
        verify(restTemplate, times(1)).getForObject("https://www.googleapis.com/books/v1/volumes/11h7DwAAQBAJ", GoogleBooksResponse.Item.class);
    }

    @Test
    void testGetRecommendations() {
        List<String> authors = List.of("Joshua Bloch", "Martin Fowler");
        List<String> categories = List.of("Programming / Software Engineering", "Software Engineering / Programming");
        GoogleBooksResponse expectedResponse = new GoogleBooksResponse();
        when(restTemplate.getForObject(anyString(), eq(GoogleBooksResponse.class)))
                .thenReturn(expectedResponse);

        GoogleBooksResponse actualResponse = connection.getRecommendations(authors, categories);

        assertEquals(expectedResponse, actualResponse);
        verify(restTemplate, times(1)).getForObject("https://www.googleapis.com/books/v1/volumes?q=inauthor:Joshua Bloch|inauthor:Martin Fowler|subject:Software Engineering", GoogleBooksResponse.class);
    }

}