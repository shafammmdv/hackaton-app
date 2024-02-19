package com.app.model.response.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrganicResult {
    int position;
    String title;
    String link;
    @JsonProperty("displayed_link")
    String displayedLink;
    String snippet;
    @JsonProperty("about_this_result")
    AboutThisResult aboutThisResult;
    @JsonProperty("about_page_link")
    String aboutPageLink;
    @JsonProperty("rich_snippet")
    RichSnippet richSnippet;
    @JsonProperty("cached_page_link")
    String cachedPageLink;
    @JsonProperty("related_questions")
    Collection<RelatedQuestion> relatedQuestions;
    @JsonProperty("related_pages_link")
    String relatedPagesLink;
}
