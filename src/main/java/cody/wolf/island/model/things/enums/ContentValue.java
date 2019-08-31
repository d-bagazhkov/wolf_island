package cody.wolf.island.model.things.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum ContentValue {
    WOLF("\uD83D\uDC3A"), RABBIT("\uD83D\uDC30"), EMPTY(""), BORDER(null);

    private final String content;

    ContentValue(String content) {
        this.content = content;
    }

    @JsonValue
    public String getContent() {
        return content;
    }
}
