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
public class SearchInformation {
    @JsonProperty("organic_results_state")
    String organicResultsState;
    @JsonProperty("query_displayed")
    String queryDisplayed;
    @JsonProperty("total_results")
    long totalResults;
    @JsonProperty("timeTakenDisplayed")
    double time_taken_displayed;
}
