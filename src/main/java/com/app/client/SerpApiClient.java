package com.app.client;

import com.app.model.response.GoogleResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "hackathon-app", url = "${endpoints.serpapi.url}")
public interface SerpApiClient {

    @GetMapping
    GoogleResponseModel findSearchResult(
            @RequestParam("api_key") String accessKey,
            @RequestParam("q") String query);
}
