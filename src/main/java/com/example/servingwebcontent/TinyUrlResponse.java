package com.example.servingwebcontent;

public class TinyUrlResponse {
    private String tinyUrl;
    private String originalUrl;

    public TinyUrlResponse(String tinyUrl, String originalUrl) {
        this.tinyUrl = tinyUrl;
        this.originalUrl = originalUrl;
    }

    public String getTinyUrl() {
        return tinyUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }
}