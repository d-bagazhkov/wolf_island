package cody.wolf.island.service.impl;

import cody.wolf.island.service.StatsService;
import org.springframework.stereotype.Service;

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
        countWolf++;
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
    public void decSteps() {
        countSteps--;
    }

    @Override
    public void decSteps(int count) {
        countSteps -= count;
    }

    @Override
    public void clear() {
        countSteps = 0;
        countWolf = 0;
        countRabbit = 0;
    }
}
