package com.app.model.response;

import com.app.model.response.object.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GoogleResponseModel {
    @JsonProperty("search_metadata")
    SearchMetadata searchMetadata;

    @JsonProperty("search_parameters")
    SearchParameters searchParameters;

    @JsonProperty("search_information")
    SearchInformation searchInformation;

    @JsonProperty("local_map")
    LocalMap localMap;

    @JsonProperty("local_results")
    LocalResults localResults;

    @JsonProperty("inline_images")
    Collection<InlineImage> inlineImages;

    @JsonProperty("organic_results")
    Collection<OrganicResult> organicResults;

    @JsonProperty("related_searches")
    Collection<RelatedSearch> relatedSearches;

    Pagination pagination;

    @JsonProperty("serpapi_pagination")
    SerpapiPagination serpapiPagination;
}
