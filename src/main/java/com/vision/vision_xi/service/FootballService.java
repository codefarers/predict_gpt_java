package com.vision.vision_xi.service;

import com.vision.vision_xi.client.FootballClient;
import org.springframework.stereotype.Service;

@Service
public class FootballService {
    private final FootballClient client;

    public FootballService(FootballClient client) {
        this.client = client;
    }

    public String getMatches() {
        return client.getPremierLeagueMatches();
    }
}
