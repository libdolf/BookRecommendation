package br.com.libdolf.bookrecommendation.connections;

import br.com.libdolf.bookrecommendation.connections.dtos.GoogleBooksResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GoogleBooksApiConnection {
    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes";
    private final RestTemplate restTemplate;

    public GoogleBooksApiConnection(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GoogleBooksResponse getBookByTitle(String title) {
        return restTemplate.getForObject(urlByTitle(title), GoogleBooksResponse.class);
    }

    public GoogleBooksResponse.Item getBookById(String id) {
        return restTemplate.getForObject(urlById(id), GoogleBooksResponse.Item.class);
    }

    public GoogleBooksResponse getRecommendations(List<String> authors, List<String> categories) {
        return restTemplate.getForObject(urlRecommendations(authors, categories), GoogleBooksResponse.class);
    }

    private String urlByTitle(String title) {
        return BASE_URL + "?q=" + title;
    }
    private String urlById(String id) {
        return BASE_URL + "/" + id;
    }
    private String urlRecommendations(List<String> authors, List<String> categories) {
        var sb = new StringBuilder(BASE_URL + "?q=");
        var queryAuthors = queryAuthors(authors);
        var queryCategories = queryCategories(categories);

        if (!queryAuthors.isEmpty()){
            sb.append(queryAuthors);
        }

        if (!queryCategories.isEmpty()) {
            if (sb.length() > (BASE_URL+"?q=").length()) {
                sb.append("|");
            }
            sb.append(queryCategories);
        }

        return sb.toString();
    }

    private String queryAuthors(List<String> authors) {
        if (authors.isEmpty()){
            return "";
        }
        return authors.stream()
                .map(author -> "inauthor:" + author)
                .collect(Collectors.joining("|"));
    }
    private String queryCategories(List<String> categories) {
        if (categories == null || categories.isEmpty()) {
            return "";
        }
        var uniqueCategories = categories.stream()
                .map(category -> category.split(" / ")[0])
                .collect(Collectors.toSet())
                .stream().toList();
        return "subject:" + uniqueCategories.getFirst();
    }
}
