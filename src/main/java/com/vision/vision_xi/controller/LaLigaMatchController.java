package com.vision.vision_xi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vision.vision_xi.dto.MatchesResponse;
import com.vision.vision_xi.service.GetLaLigaMatchesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/laliga")
public class LaLigaMatchController {
    private final GetLaLigaMatchesService service;

    public LaLigaMatchController(GetLaLigaMatchesService service) {
        this.service = service;
    }

    @GetMapping("all-matches")
    public MatchesResponse getLaligaMatches() throws JsonProcessingException {
        return service.getMatches();
    }
}
