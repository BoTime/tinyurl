package com.example.servingwebcontent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletResponse;

// https://www.baeldung.com/spring-redirect-and-forward
// https://www.baeldung.com/spring-request-param
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class TinyUrlController {
	private final static Logger LOG = LoggerFactory.getLogger(TinyUrlController.class);
	@Autowired
	private TinyUrlService tinyUrlService;

	@GetMapping("/tinyurl")
	public String greeting(Model model) {
		return "tinyurl";
	}

	@GetMapping("/redirect")
	public String redirectToOriginalUrl(@RequestParam(name = "url") String tinyUrl) {
		String originalUrl = tinyUrlService.getOriginalUrl(tinyUrl); 
		if (originalUrl.equals("")) {
			LOG.error(tinyUrl + " not found");
			return "tinyurl";
		}
		LOG.info("redirect to: " + originalUrl);
		return "redirect:" + originalUrl;
	}


	@PostMapping("/tinyurl")
	@ResponseBody
	public TinyUrlResponse generateTinyUrl(@RequestBody TinyUrlRequest tinyUrlRequest) {
		// Check if tinyurl exists
		// If exists, return directly
		// If not exists, generate tinyurl and save to database
		String originalUrl = tinyUrlRequest.getOriginalUrl();
		String tinyUrl = TinyUrlGenerator.generate(originalUrl);

		// tinyUrl and originalUrl are already in database
		String url = tinyUrlService.getOriginalUrl(tinyUrl);
		if (url.equals(originalUrl)) {
			LOG.info(tinyUrl + " already exists as: " + url);
			return new TinyUrlResponse(tinyUrl, originalUrl);
		}

		if (url.equals("")) {
			LOG.info("Saving " + tinyUrl + " to database as: " + originalUrl);
			tinyUrlService.saveUrlMapping(new UrlMapping(tinyUrl, originalUrl));
			return new TinyUrlResponse(tinyUrl, originalUrl);	
		}

		String toBeHashed = new String(originalUrl);
		do {
			LOG.info(toBeHashed + " already exists");
			toBeHashed = toBeHashed + "_UNIQUEPLACEHOLDER";
			tinyUrl = TinyUrlGenerator.generate(toBeHashed);
		} while (tinyUrlService.tinyUrlExists(tinyUrl));

		UrlMapping urlMapping = new UrlMapping(tinyUrl, toBeHashed);	
		int result = tinyUrlService.saveUrlMapping(urlMapping);
		TinyUrlResponse response = new TinyUrlResponse(tinyUrl, originalUrl);
		return response; 
	}
}
