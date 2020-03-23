package com.example.servingwebcontent;

public interface TinyUrlService {
    /**
     * Persist tinyUrl to database
     * @return 0 if success, -1 on failure
     */
    public int saveUrlMapping(UrlMapping urlMapping);

    /**
     * Check if tinyUrl already exists
     * @param tinyUrl
     * @return
     */
    public boolean tinyUrlExists(String tinyUrl);

    /**
     * Read originalUrl from database 
     * @param tinyUrl
     * @return
     */
    public String getOriginalUrl(String tinyUrl);
}