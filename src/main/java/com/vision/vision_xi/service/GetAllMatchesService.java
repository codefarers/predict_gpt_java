package com.vision.vision_xi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vision.vision_xi.client.FootballClient;
import com.vision.vision_xi.dto.MatchesResponse;
import org.springframework.stereotype.Service;

@Service
public class GetAllMatchesService {
    private final FootballClient client;
    private final ObjectMapper objectMapper;

    public GetAllMatchesService(FootballClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public MatchesResponse getMatches() {
        try {
            String response = client.getAllMatches();
            return objectMapper.readValue(response, MatchesResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse matches response", e);
        }
    }

}
