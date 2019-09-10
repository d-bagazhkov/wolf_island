package cody.wolf.island.service.impl;

import cody.wolf.island.model.things.enums.ContentValue;
import cody.wolf.island.service.StatsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StatsServiceImpl implements StatsService {

    private int countWolf;
    private int countRabbit;
    private int countSteps;

    @Override
    public int getCountWolf() {
        return countWolf;
    }

    @Override
    public int getCountRabbit() {
        return countRabbit;
    }

    @Override
    public int getCountSteps() {
        return countSteps;
    }

    @Override
    public void incWolf() {
        incWolf(1);
    }

    @Override
    public void incWolf(int count) {
        countWolf += count;
    }

    @Override
    public void incRabbit() {
        countRabbit++;
    }

    @Override
    public void incRabbit(int count) {
        countRabbit += count;
    }

    @Override
    public void incSteps() {
        countSteps++;
    }

    @Override
    public void incSteps(int count) {
        countSteps += count;
    }

    @Override
    public void decWolf() {
        countWolf--;
    }

    @Override
    public void decWolf(int count) {
        countWolf -= count;
    }

    @Override
    public void decRabbit() {
        countRabbit--;
    }

    @Override
    public void decRabbit(int count) {
        countRabbit -= count;
    }

    @Override
    public void incInstance(ContentValue contentValue) {
        if (contentValue.equals(ContentValue.WOLF))
            incWolf();
        else if (contentValue.equals(ContentValue.RABBIT))
            incRabbit();
    }

    @Override
    public void decInstance(ContentValue contentValue) {
        if (contentValue.equals(ContentValue.WOLF))
            decWolf();
        else if (contentValue.equals(ContentValue.RABBIT))
            decRabbit();
    }

    @Override
    public void decSteps() {
        countSteps--;
    }

    @Override
    public void decSteps(int count) {
        countSteps -= count;
    }

    @Override
    public void clearStats() {
        countSteps = 0;
        countWolf = 0;
        countRabbit = 0;
    }
}
