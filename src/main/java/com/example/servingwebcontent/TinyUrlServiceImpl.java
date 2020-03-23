package com.example.servingwebcontent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TinyUrlServiceImpl implements TinyUrlService {
	private final static Logger LOG = LoggerFactory.getLogger(TinyUrlServiceImpl.class);
    @Autowired 
    private UrlMappingRepository urlMappingRepository;

    @Override
    public int saveUrlMapping(UrlMapping urlMapping) {
        UrlMapping savedMapping = urlMappingRepository.save(urlMapping);
        if (savedMapping == null) {
            return -1;
        }
        return 1;
    }
    
    @Override
    public boolean tinyUrlExists(String tinyUrl) {
        List<UrlMapping> results = urlMappingRepository.findByTinyUrl(tinyUrl);
        // LOG.info(tinyUrl); 
        if (results == null || results.size() == 0) {
            return false;
        }
        LOG.info("====>");
        return true;
    }

    @Override
    public String getOriginalUrl(String tinyUrl) {
        List<UrlMapping> list = urlMappingRepository.findByTinyUrl(tinyUrl);
        if (list == null || list.size() == 0) {
            return "";
        }
        UrlMapping urlMapping = list.get(0);
        String orignalUrl = urlMapping.getOriginalUrl();
        // String orignalUrl = "";
        return orignalUrl;
    }
}