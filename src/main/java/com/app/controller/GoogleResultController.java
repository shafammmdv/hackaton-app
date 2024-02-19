package com.app.controller;

import com.app.client.SerpApiClient;
import com.app.model.request.GoogleRequestModel;
import com.app.model.response.GoogleResponseModel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class GoogleResultController {

    private final SerpApiClient serpApiClient;

    @PostMapping
    public ResponseEntity<GoogleResponseModel> getSearchResults(@RequestBody GoogleRequestModel request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(serpApiClient.findSearchResult(request.getKey(), request.getQuery()));
    }
}
