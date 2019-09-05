package cody.wolf.island.model;

import cody.wolf.island.model.things.Thing;
import cody.wolf.island.model.things.enums.ContentValue;

public interface Ceil {

    Thing getThing();
    void setThing(Thing thing);
    Position getPosition();
    void setPosition(Position position);
    boolean hasContent(ContentValue contentValue);

}
