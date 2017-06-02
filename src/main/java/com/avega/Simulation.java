package com.avega;

import com.avega.config.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Simulation {

    public static int DEFAULT_NUMBER_OF_ITERATIONS = 1000;
    public static void main(String[] args) {

        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        int numberOfIterations = (args != null && args.length>0) ? Integer.parseInt(args[0]) : DEFAULT_NUMBER_OF_ITERATIONS;

        Game game = context.getBean(Game.class);
        List<GameState> gameStateList = new ArrayList<>();

        for (int i = 0; i < numberOfIterations; i++) {
            Arrays.stream(GameStrategy.values())
                    .forEach((GameStrategy gameStrategy) -> {
                        gameStateList.add(game.play(gameStrategy));
                    });
        }

        Map<GameStrategy, Long> results = gameStateList.stream()
                .filter(GameState::isWon)
                .collect(Collectors.groupingBy(GameState::getGameStrategy, Collectors.counting()));


        System.out.println("With number of iterations = " + numberOfIterations);
        results.forEach((gameStrategy, winCount) -> {
            System.out.println(String.format("Winning chance with game strategy %s is %d%%", gameStrategy.name(), (winCount*100)/numberOfIterations));
        });
    }
}
