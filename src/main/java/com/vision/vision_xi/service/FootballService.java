package com.vision.vision_xi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vision.vision_xi.client.FootballClient;
import org.springframework.stereotype.Service;
import com.vision.vision_xi.dto.MatchesResponse;

@Service
public class FootballService {

    private final FootballClient client;
    private final ObjectMapper objectMapper;

    public FootballService(FootballClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public MatchesResponse getMatches() {
        try {
            String response = client.getPremierLeagueMatches();
            return objectMapper.readValue(response, MatchesResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse matches response", e);
        }
    }
}