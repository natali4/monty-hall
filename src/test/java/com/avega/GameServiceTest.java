package com.avega;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @InjectMocks
    private GameService gameService;
    @Mock
    private RandomNumberGenerator randomNumberGenerator;

    private GameState gameState = new GameState();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void can_choose_random_box() {
        List<Box> boxes = Arrays.asList(new Box(1), new Box(2), new Box(3));
        gameState.setBoxes(boxes);

        when(randomNumberGenerator.generate(0, 2)).thenReturn(2);

        int choosenBoxNumber = gameService.chooseBoxNumber(boxes);

        assertEquals(3, choosenBoxNumber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throws_IAE_when_boxes_is_null() {
        gameService.chooseBoxNumber(null);
    }

    @Test(expected = IllegalArgumentException.class)

    public void throws_IAE_when_boxes_is_empty() {
        gameService.chooseBoxNumber(new ArrayList<>());
    }

    @Test
    public void can_find_box_to_open() {
        List<Box> boxes = Arrays.asList(new Box(1), new Box(2), new Box(3));
        gameState.setBoxes(boxes);
        gameState.setChosenBoxNumber(1);
        gameState.setWinningBoxNumber(2);
        int boxNumberToOpen = gameService.findBoxNumberToOpen(gameState);

        assertEquals(3, boxNumberToOpen);
    }

    @Test
    public void can_find_box_to_switch_to() {

        Box box3 = new Box(3);
        box3.open();
        List<Box> boxes = Arrays.asList(new Box(1), new Box(2), box3);

        gameState.setBoxes(boxes);
        gameState.setChosenBoxNumber(1);
        gameState.setWinningBoxNumber(2);

        int numberOfBoxToSwitchTo = gameService.findBoxNumberToSwitchTo(gameState);

        assertEquals(2, numberOfBoxToSwitchTo);
    }

}