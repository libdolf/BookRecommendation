package br.com.libdolf.bookrecommendation.connections.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleBooksResponse {
    private Integer totalItems;
    private List<Item> items;

    @Data
    @EqualsAndHashCode
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private String id;
        private VolumeInfo volumeInfo;
    }

    @Data
    @EqualsAndHashCode
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VolumeInfo {
        private String title;
        private List<String> authors;
        private String publisher;
        private String publishedDate;
        private String description;
        private Integer pageCount;
        private List<String> categories;
    }
}
