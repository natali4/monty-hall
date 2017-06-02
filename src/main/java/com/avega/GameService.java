package com.avega;

import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Component
public class GameService {

    @Inject
    private RandomNumberGenerator randomNumberGenerator;

    public int chooseBoxNumber(List<Box> boxes) {
        if (boxes == null || boxes.isEmpty()) {
            throw new IllegalArgumentException("List of boxes can not be null or empty");
        }

        Box chosenBox = boxes.get(randomNumberGenerator.generate(0, boxes.size() - 1));

        return chosenBox.getNumber();
    }

    public int findBoxNumberToOpen(GameState gameState) {
        Optional<Box> boxToOpen = gameState.getBoxes().stream()
                .filter(box -> box.getNumber() != gameState.getChosenBoxNumber() && box.getNumber() != gameState.getWinningBoxNumber())
                .findAny();

        if (!boxToOpen.isPresent()) {
            throw new IllegalArgumentException("There is no box to open");
        }

        return boxToOpen.get().getNumber();
    }

    public int findBoxNumberToSwitchTo(GameState gameState) {
        Optional<Box> boxToSwitchTo = gameState.getBoxes().stream()
                .filter(box -> box.getNumber() != gameState.getChosenBoxNumber() && !box.isOpen())
                .findAny();

        return boxToSwitchTo.get().getNumber();
    }
}
