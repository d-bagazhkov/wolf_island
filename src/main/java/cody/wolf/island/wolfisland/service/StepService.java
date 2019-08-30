package cody.wolf.island.wolfisland.service;

import cody.wolf.island.wolfisland.model.Entity;

import java.util.List;

public interface StepService {

    List<Entity> handle();
    List<Entity> reset();
}
