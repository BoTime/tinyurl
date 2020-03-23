package com.example.servingwebcontent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "url_mapping")
public class UrlMapping {
  @Id
  private String tinyUrl;
  private String originalUrl;
  protected UrlMapping() {}

  public UrlMapping(String tinyUrl, String originalUrl) {
    this.tinyUrl = tinyUrl;
    this.originalUrl = originalUrl;
  }

  @Override
  public String toString() {
    return String.format(
      "UrlMapping[tinyUrl=%s, originalUrl='%s']",
      tinyUrl,
      originalUrl
    );
  }

  public String getTinyUrl() {
    return tinyUrl;
  }

  public String getOriginalUrl() {
    return originalUrl;
  }
}