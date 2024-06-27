package br.com.libdolf.bookrecommendation.utils;

import br.com.libdolf.bookrecommendation.connections.dtos.GoogleBooksResponse;

import java.util.List;

public class GBResponseMock {
    public static GoogleBooksResponse oneElement(){
        var volumeInfo = new GoogleBooksResponse.VolumeInfo();
        volumeInfo.setTitle("Harry Potter e a Criança Amaldiçoada - Partes Um e Dois");
        volumeInfo.setAuthors(List.of("J.K. Rowling", "John Tiffany", "Jack Thorne"));
        volumeInfo.setDescription("Description");
        volumeInfo.setPublisher("Pottermore Publishing");
        volumeInfo.setPageCount(437);
        volumeInfo.setPublishedDate("2018-09-19");
        volumeInfo.setCategories(List.of("Drama"));


        var item = new GoogleBooksResponse.Item();
        item.setId("11h7DwAAQBAJ");
        item.setVolumeInfo(volumeInfo);

        var response = new GoogleBooksResponse();
        response.setTotalItems(1);
        response.setItems(List.of(item));

        return response;
    }

    public static GoogleBooksResponse twoElements(){
        var volumeInfo = new GoogleBooksResponse.VolumeInfo();
        volumeInfo.setTitle("Harry Potter e a Criança Amaldiçoada - Partes Um e Dois");
        volumeInfo.setAuthors(List.of("J.K. Rowling", "John Tiffany", "Jack Thorne"));
        volumeInfo.setDescription("Description");
        volumeInfo.setPublisher("Pottermore Publishing");
        volumeInfo.setPageCount(437);
        volumeInfo.setPublishedDate("2018-09-19");
        volumeInfo.setCategories(List.of("Drama"));


        var item1 = new GoogleBooksResponse.Item();
        item1.setId("11h7DwAAQBAJ");
        item1.setVolumeInfo(volumeInfo);

        var item2 = new GoogleBooksResponse.Item();
        item2.setId("22h7DwAAQBAJ");
        item2.setVolumeInfo(volumeInfo);
        var response = new GoogleBooksResponse();
        response.setTotalItems(1);
        response.setItems(List.of(item1, item2));

        return response;
    }

    public static GoogleBooksResponse zeroElements(){
        var response = new GoogleBooksResponse();
        response.setTotalItems(0);
        response.setItems(null);
        return response;
    }
}
