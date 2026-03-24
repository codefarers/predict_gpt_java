package com.vision.vision_xi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vision.vision_xi.client.FootballClient;
import com.vision.vision_xi.dto.MatchesResponse;
import org.springframework.stereotype.Service;

@Service
public class BundesligaMatchesService {
    private final FootballClient client;
    private final ObjectMapper objectMapper;

    public BundesligaMatchesService(FootballClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public MatchesResponse getMatches() throws JsonProcessingException {
        try {
            String response = client.getBundesligaMatches();
            return objectMapper.readValue(response, MatchesResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse matches response");
        }
    }

}
