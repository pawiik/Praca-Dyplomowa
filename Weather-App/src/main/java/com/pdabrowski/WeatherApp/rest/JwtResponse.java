package com.pdabrowski.WeatherApp.rest;

public class JwtResponse {
    private String jwtToken;

    // You need a default constructor for Jackson
    public JwtResponse() {
    }

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    // Getter method for Jackson to use during serialization
    public String getJwtToken() {
        return jwtToken;
    }

    // If you have setters, ensure they are public too
    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
