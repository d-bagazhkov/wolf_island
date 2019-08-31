package cody.wolf.island.model.things.animal;

import cody.wolf.island.model.things.Thing;

public interface AnimalThing extends Thing {

    @Override
    default boolean isMovable() {
        return true;
    }
}
