package com.vision.vision_xi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vision.vision_xi.dto.MatchesResponse;
import com.vision.vision_xi.service.BundesligaMatchesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/bundesliga")
public class BundesligaMatchesController {
    private final BundesligaMatchesService service;

    public BundesligaMatchesController(BundesligaMatchesService service) {
        this.service = service;
    }

    @GetMapping("matches")
    public MatchesResponse getBundesligaMatches() throws JsonProcessingException {
        return service.getMatches();
    }
}
