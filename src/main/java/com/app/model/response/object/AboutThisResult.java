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
public class AboutThisResult {
    Source source;
    Collection<String> keywords;
    @JsonProperty("related_keywords")
    Collection<String> relatedKeywords;
    Collection<String> languages;
    Collection<String> regions;
}
