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
public class OtherPages {
    @JsonProperty("2")
    public String _2;
    @JsonProperty("3")
    public String _3;
    @JsonProperty("4")
    public String _4;
    @JsonProperty("5")
    public String _5;}
