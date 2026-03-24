package com.vision.vision_xi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vision.vision_xi.dto.MatchesResponse;
import com.vision.vision_xi.service.GetLigueOneMatches;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ligue-one")
public class LigueOneController {
    private final GetLigueOneMatches service;

    public LigueOneController(GetLigueOneMatches service){this.service = service; }

    @GetMapping("matches")
    public MatchesResponse getLigueOneMatches() throws JsonProcessingException {
     return service.getMatches();
    }
}
