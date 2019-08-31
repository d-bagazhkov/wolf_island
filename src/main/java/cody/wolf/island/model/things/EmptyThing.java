package cody.wolf.island.model.things;

import cody.wolf.island.model.things.enums.ContentValue;
import lombok.ToString;

@ToString
public class EmptyThing implements Thing {

    @Override
    public ContentValue getValue() {
        return ContentValue.EMPTY;
    }
}
