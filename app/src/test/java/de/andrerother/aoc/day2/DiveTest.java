package de.andrerother.aoc.day2;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static de.andrerother.aoc.day2.Dive.MOVEMENT_FORWARD;
import static de.andrerother.aoc.day2.Dive.MOVEMENT_UP;
import static de.andrerother.aoc.day2.Dive.MOVEMENT_DOWN;

class DiveTest {
    Dive underTest = new Dive();

    @Test
    void count_movements() {
        int result = underTest.pilot(createDemoMovements());
        assertEquals(150, result);
    }

    @Test
    void count_movements_with_aim() {
        int result = underTest.pilotToAim(createDemoMovements());
        assertEquals(900, result);
    }

    private List<Pair<String, Integer>> createDemoMovements() {
        List<Pair<String, Integer>> demoMovements = new ArrayList<>();
        demoMovements.add(new ImmutablePair<>(MOVEMENT_FORWARD, 5));
        demoMovements.add(new ImmutablePair<>(MOVEMENT_DOWN, 5));
        demoMovements.add(new ImmutablePair<>(MOVEMENT_FORWARD, 8));
        demoMovements.add(new ImmutablePair<>(MOVEMENT_UP, 3));
        demoMovements.add(new ImmutablePair<>(MOVEMENT_DOWN, 8));
        demoMovements.add(new ImmutablePair<>(MOVEMENT_FORWARD, 2));
        return demoMovements;
    }
}