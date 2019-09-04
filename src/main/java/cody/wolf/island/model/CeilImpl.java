package cody.wolf.island.model;

import cody.wolf.island.model.things.EmptyThing;
import cody.wolf.island.model.things.Thing;
import cody.wolf.island.model.things.enums.ContentValue;
import lombok.Data;

@Data
public class CeilImpl implements Ceil {

    private Thing thing;
    private Position position;
    private boolean isDocked;

    public CeilImpl(Position position) {
        this.thing = new EmptyThing();
        this.position = position;
        this.isDocked = false;
    }

    @Override
    public boolean hasContent(ContentValue contentValue) {
        return this.thing.getValue().equals(contentValue);
    }
}
