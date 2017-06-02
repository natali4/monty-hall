package com.avega;

import java.util.Arrays;
import java.util.List;

public class GameState {

    private List<Box> boxes;
    private GameStrategy gameStrategy = GameStrategy.ALWAYS_KEEP;
    private int winningBoxNumber = -1;
    private int chosenBoxNumber = -1;

    public GameState() {
        boxes = Arrays.asList(new Box(1), new Box(2), new Box(3));
    }

    public void setWinningBoxNumber(int winningBoxNumber) {
        this.winningBoxNumber = winningBoxNumber;
    }

    public void setChosenBoxNumber(int chosenBoxNumber) {
        this.chosenBoxNumber = chosenBoxNumber;
    }

    public void setGameStrategy(GameStrategy gameStrategy) {
        this.gameStrategy = gameStrategy;
    }

    public GameStrategy getGameStrategy() {
        return gameStrategy;
    }

    public boolean isWon() {
        return chosenBoxNumber == winningBoxNumber;
    }

    public void openBox(int boxNumber) {
        boxes.forEach(box -> {
            if (box.getNumber() == boxNumber) {
                box.open();
            }}
        );
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public int getChosenBoxNumber() {
        return chosenBoxNumber;
    }

    public int getWinningBoxNumber() {
        return winningBoxNumber;
    }

    public void setBoxes(List<Box> boxes) {
        this.boxes = boxes;
    }

}
