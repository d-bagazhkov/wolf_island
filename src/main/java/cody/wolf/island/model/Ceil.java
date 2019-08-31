package cody.wolf.island.model;

import cody.wolf.island.model.things.EmptyThing;
import cody.wolf.island.model.things.Thing;
import lombok.Data;

@Data
public class Ceil {

    private Thing thing;
    private Position position;

    public Ceil(Position position) {
        this.thing = new EmptyThing();
        this.position = position;
    }

}
