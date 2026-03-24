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

    // ---------- PUBLIC METHODS ----------

    public String getAllMatches() {
        return getRequest(baseUrl + "matches", String.class);
    }

    public String getPremierLeagueMatches() {
        return getMatchesByCompetition("PL");
    }

    public String getLaLigaMatches() {
        return getMatchesByCompetition("PD"); // correct La Liga code
    }

    public String getLigueOneMatches () {
        return getMatchesByCompetition("FL1");
    }

    public String getBundesligaMatches () {
        return getMatchesByCompetition("BL1");
    }

    // ---------- CORE LOGIC ----------

    private String getMatchesByCompetition(String competitionCode) {
        Map<String, Object> competition = getRequest(baseUrl + "competitions/" + competitionCode, Map.class);

        Integer matchday = extractMatchday(competition);

        String url = baseUrl + "competitions/" + competitionCode +
                "/matches?matchday=" + matchday;

        return getRequest(url, String.class);
    }

    // ---------- HELPERS ----------

    private <T> T getRequest(String url, Class<T> responseType) {
        ResponseEntity<T> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                buildEntity(),
                responseType
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Request failed: " + url);
        }

        return response.getBody();
    }

    private HttpEntity<String> buildEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", apiKey);
        return new HttpEntity<>(headers);
    }

    @SuppressWarnings("unchecked")
    private Integer extractMatchday(Map<String, Object> body) {
        if (body == null) {
            throw new RuntimeException("Response body is null");
        }

        Object seasonObj = body.get("currentSeason");
        if (!(seasonObj instanceof Map)) {
            throw new RuntimeException("Invalid currentSeason");
        }

        Map<String, Object> season = (Map<String, Object>) seasonObj;

        Object matchdayObj = season.get("currentMatchday");
        if (!(matchdayObj instanceof Integer)) {
            throw new RuntimeException("Invalid currentMatchday");
        }

        return (Integer) matchdayObj;
    }
}
