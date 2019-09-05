package cody.wolf.island.model.things.animal;

import cody.wolf.island.model.things.enums.ContentValue;
import lombok.ToString;

@ToString
public class Wolf implements AnimalThing {

    private final ContentValue value = ContentValue.WOLF;
    private int energy = 100;
    private int age = 0;

    @Override
    public ContentValue getValue() {
        return value;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public void incEnergy(int amount) {
        energy += amount;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void incAge() {
        age++;
    }

    @Override
    public void decEnergy(int amount) {
        energy -= amount;
    }
}
