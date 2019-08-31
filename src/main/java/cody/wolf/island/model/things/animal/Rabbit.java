package cody.wolf.island.model.things.animal;

import cody.wolf.island.model.things.enums.ContentValue;

public class Rabbit implements AnimalThing {

    private final ContentValue value = ContentValue.RABBIT;

    @Override
    public ContentValue getValue() {
        return value;
    }
}
