package com.vision.vision_xi.controller;

import com.vision.vision_xi.dto.MatchesResponse;
import com.vision.vision_xi.service.GetAllMatchesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/today-matches")
public class GetTodayMatchesController {
    private final GetAllMatchesService service;
    
    public GetTodayMatchesController(GetAllMatchesService service) {
        this.service = service;
    }

    @GetMapping("/matches")
    public MatchesResponse getAllMatches(){
        return service.getMatches();
    }
}
