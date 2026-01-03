package com.filmaddix.backend.dto;

public class RatingDto {

    private String source;
    private Double value;

    public RatingDto(String source, Double value) {
        this.source = source;
        this.value = value;
    }

    public String getSource() { return source; }
    public Double getValue() { return value; }
}
