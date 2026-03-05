package com.vision.vision_xi.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Map;

@Component
public class FootballClient {
    @Value("${football.api.base-url}")
    private String baseUrl;

    @Value("${football.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getPremierLeagueMatches() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String competitionUrl = baseUrl + "competitions/PL";

        ResponseEntity<Map> competitionResponse = restTemplate.exchange(
                competitionUrl,
                HttpMethod.GET,
                entity,
                Map.class

        );

        if (!competitionResponse.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to fetch competition data");
        }

        Map body = competitionResponse.getBody();
        if(body == null) {
            throw new RuntimeException("Competition response body is null");
        }

        Object seasonObj = body.get("currentSeason");
        if(!(seasonObj instanceof Map)) {
            throw new RuntimeException("Current season missing or invalid");
        }

        Map currentSeason = (Map) seasonObj;

        Object matchdayObj = currentSeason.get("currentMatchday");
        if(!(matchdayObj instanceof Integer)) {
            throw new RuntimeException("current Matchday missing or invalid");
        }

        Integer matchday = (Integer) matchdayObj;

        String matchesUrl = baseUrl + "/competitions/PL/matches?matchday" + matchday;

        ResponseEntity<String> matchesResponse = restTemplate.exchange(
                matchesUrl,
                HttpMethod.GET,
                entity,
                String.class
        );

        if(!matchesResponse.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to fetch matches");
        }

        return matchesResponse.getBody();

    }
}
