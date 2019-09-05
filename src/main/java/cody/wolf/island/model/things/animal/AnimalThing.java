package cody.wolf.island.model.things.animal;

import cody.wolf.island.model.things.Thing;

import javax.validation.constraints.Positive;

public interface AnimalThing extends Thing {

    @Override
    default boolean isMovable() {
        return true;
    }

    int getAge();

    void setAge(@Positive int age);

    void incAge();

    void decEnergy(@Positive int amount);

    int getEnergy();

    void incEnergy(@Positive int amount);

}
