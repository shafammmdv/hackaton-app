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
public class Place {
    int position;
    String title;
    @JsonProperty("place_id")
    String placeId;
    String lsig;
    @JsonProperty("place_id_search")
    String placeIdSearch;
    String description;
    String rating;
    Links links;
    String address;
    String hours;
    @JsonProperty("gps_coordinates")
    GpsCoordinates gpsCoordinates;
}
