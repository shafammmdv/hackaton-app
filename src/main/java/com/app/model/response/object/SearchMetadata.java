package com.app.model.response.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchMetadata {
    String id;
    String status;
    @JsonProperty("json_endpoint")
    String jsonEndpoint;
    @JsonProperty("created_at")
    String createdAt;
    @JsonProperty("processed_at")
    String processedAt;
    @JsonProperty("google_url")
    String googleUrl;
    @JsonProperty("raw_html_file")
    String rawHtmlFile;
    @JsonProperty("total_time_taken")
    double totalTimeTaken;
}
