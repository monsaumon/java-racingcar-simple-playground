import java.util.List;

public class Application {
    public static void main(String[] args) {
        IO io = new IO();
        final String[] carNames = io.getCarNames();
        final int moveCount = io.getMoveCount();

        Race race = new Race(carNames);
        RandomGenerator generator = new RandomGenerator();
        final int[][] moveHistory = race.getMoveHistory(moveCount,
                generator.generateRandom2DArray(carNames.length, moveCount));
        final List<String> winners = race.getWinners(moveHistory);

        io.printRaceResult(moveHistory, carNames, winners);
    }
}
