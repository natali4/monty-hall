package com.avega;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;

public class GameTest {
    @Mock
    private GameService gameService;
    @Mock
    private RandomNumberGenerator randomNumberGenerator;

    @InjectMocks
    private Game game;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void can_play_game_with_ALWAYS_KEEP_strategy_win() {
        int winningBoxNumber = 2;
        int chosenBoxNumber = 2;
        when(randomNumberGenerator.generate(1, 3)).thenReturn(winningBoxNumber);
        when(gameService.chooseBoxNumber(anyList())).thenReturn(chosenBoxNumber);
        when(gameService.findBoxNumberToOpen(any())).thenReturn(3);

        GameState gameState = game.play(GameStrategy.ALWAYS_KEEP);

        assertTrue(gameState.isWon());
    }

    @Test
    public void can_play_game_with_ALWAYS_KEEP_strategy_loose() {

        int winningBoxNumber = 2;
        int chosenBoxNumber = 1;
        when(randomNumberGenerator.generate(1, 3)).thenReturn(winningBoxNumber);
        when(gameService.chooseBoxNumber(anyList())).thenReturn(chosenBoxNumber);
        when(gameService.findBoxNumberToOpen(any())).thenReturn(3);

        GameState gameState = game.play(GameStrategy.ALWAYS_KEEP);

        assertFalse(gameState.isWon());
    }

    @Test
    public void can_play_game_with_ALWAYS_SWITCH_strategy_win() {

        int winningBoxNumber = 2;
        int chosenBoxNumber = 1;
        when(randomNumberGenerator.generate(1, 3)).thenReturn(winningBoxNumber);
        when(gameService.chooseBoxNumber(anyList())).thenReturn(chosenBoxNumber);
        when(gameService.findBoxNumberToOpen(any())).thenReturn(3);
        when(gameService.findBoxNumberToSwitchTo(any())).thenReturn(winningBoxNumber);

        GameState gameState = game.play(GameStrategy.ALWAYS_SWITCH);

        assertTrue(gameState.isWon());
    }

    @Test
    public void can_play_game_with_ALWAYS_SWITCH_strategy_loose() {

        int winningBoxNumber = 2;
        int chosenBoxNumber = 2;
        when(randomNumberGenerator.generate(1, 3)).thenReturn(winningBoxNumber);
        when(gameService.chooseBoxNumber(anyList())).thenReturn(chosenBoxNumber);
        when(gameService.findBoxNumberToOpen(any())).thenReturn(3);
        when(gameService.findBoxNumberToSwitchTo(any())).thenReturn(1);

        GameState gameState = game.play(GameStrategy.ALWAYS_SWITCH);

        assertFalse(gameState.isWon());
    }
}