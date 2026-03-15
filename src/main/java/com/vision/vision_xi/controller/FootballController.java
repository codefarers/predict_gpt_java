package com.vision.vision_xi.controller;

import com.vision.vision_xi.dto.MatchesResponse;
import com.vision.vision_xi.service.FootballService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/premierleague")
public class FootballController {

    private final FootballService service;

    public FootballController(FootballService service) {
        this.service = service;
    }

    @GetMapping("/matches")
    public MatchesResponse getMatches() {
        return service.getMatches();
    }
}