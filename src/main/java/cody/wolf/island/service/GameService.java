package cody.wolf.island.service;

import cody.wolf.island.service.impl.TableServiceImpl;

public interface GameService {

    TableServiceImpl handle();

    TableServiceImpl reset();

}
