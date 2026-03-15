package com.vision.vision_xi.dto;

public class Score {

    private String winner;
    private String duration;
    private ScoreDetail fullTime;
    private ScoreDetail halfTime;

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public ScoreDetail getFullTime() {
        return fullTime;
    }

    public void setFullTime(ScoreDetail fullTime) {
        this.fullTime = fullTime;
    }

    public ScoreDetail getHalfTime() {
        return halfTime;
    }

    public void setHalfTime(ScoreDetail halfTime) {
        this.halfTime = halfTime;
    }
}