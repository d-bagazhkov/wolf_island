package cody.wolf.island.model;

import cody.wolf.island.model.things.Thing;
import cody.wolf.island.model.things.enums.ContentValue;

public interface Cell {

    Thing getThing();
    void setThing(Thing thing);
    Position getPosition();
    boolean hasContent(ContentValue contentValue);

}
