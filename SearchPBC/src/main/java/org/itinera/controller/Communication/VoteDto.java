package org.itinera.controller.Communication;

public class VoteDto {
    private String email;
    private float vote;

    public VoteDto(String email, float vote) {
        this.email = email;
        this.vote = vote;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getVote() {
        return vote;
    }

    public void setVote(float vote) {
        this.vote = vote;
    }
}
