package com.avega;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class Game {

    @Inject
    private RandomNumberGenerator randomNumberGenerator;

    @Inject
    private GameService gameService;

    public GameState play(GameStrategy gameStrategy) {

        GameState gameState = new GameState();
        gameState.setGameStrategy(gameStrategy);

        gameState.setWinningBoxNumber(randomNumberGenerator.generate(1, 3));

        gameState.setChosenBoxNumber(gameService.chooseBoxNumber(gameState.getBoxes()));

        gameState.openBox(gameService.findBoxNumberToOpen(gameState));

        if (GameStrategy.ALWAYS_SWITCH.equals(gameStrategy)) {
            gameState.setChosenBoxNumber(gameService.findBoxNumberToSwitchTo(gameState));
        }

        return gameState;
    }

}
