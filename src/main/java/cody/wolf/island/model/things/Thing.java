package cody.wolf.island.model.things;

import cody.wolf.island.model.things.enums.ContentValue;
import com.fasterxml.jackson.annotation.JsonProperty;

public interface Thing {

    @JsonProperty("value")
    ContentValue getValue();

    default boolean isMovable() {
        return false;
    }

}
