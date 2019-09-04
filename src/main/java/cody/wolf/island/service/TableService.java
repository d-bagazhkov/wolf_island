package cody.wolf.island.service;

import cody.wolf.island.model.Ceil;
import cody.wolf.island.model.Position;

public interface TableService {

    Ceil get(int x, int y);
    Ceil get(Position position);

}
