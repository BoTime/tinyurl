package com.example.servingwebcontent;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UrlMappingRepository extends CrudRepository<UrlMapping, String> {
    List<UrlMapping> findByTinyUrl(String tinyUrl);
}