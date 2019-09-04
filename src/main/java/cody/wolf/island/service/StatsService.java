package cody.wolf.island.service;

import javax.validation.constraints.Positive;

public interface StatsService {

    int getCountWolf();

    int getCountRabbit();

    int getCountSteps();

    void incWolf();

    void incWolf(@Positive int count);

    void incRabbit();

    void incRabbit(@Positive int count);

    void incSteps();

    void incSteps(@Positive int count);

    void decWolf();

    void decWolf(@Positive int count);

    void decRabbit();

    void decRabbit(@Positive int count);

    void decSteps();

    void decSteps(@Positive int count);

}
