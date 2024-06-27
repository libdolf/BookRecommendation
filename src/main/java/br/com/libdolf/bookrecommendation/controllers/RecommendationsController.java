package br.com.libdolf.bookrecommendation.controllers;

import br.com.libdolf.bookrecommendation.controllers.dtos.BookResponse;
import br.com.libdolf.bookrecommendation.services.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/recommendations")
public class RecommendationsController {
    private final RecommendationService recommendationService;

    public RecommendationsController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<BookResponse>> getRecommendations(@PathVariable String id) {
        return ResponseEntity.ok(recommendationService.getRecommendations(id));
    }
}
