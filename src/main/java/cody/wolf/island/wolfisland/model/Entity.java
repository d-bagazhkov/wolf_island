package cody.wolf.island.wolfisland.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entity {
    @JsonProperty("name")
    private String entityName;
    private Position position;
}
