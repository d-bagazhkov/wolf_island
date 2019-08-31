package cody.wolf.island.model.things.animal;

import cody.wolf.island.model.things.enums.ContentValue;

public class Wolf implements AnimalThing {

    private final ContentValue value = ContentValue.WOLF;

    @Override
    public ContentValue getValue() {
        return value;
    }
}