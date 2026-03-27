import domain.Race;
import domain.RandomGenerator;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final String[] carNames = inputView.getCarNames();
        final int moveCount = inputView.getMoveCount();

        Race race = new Race(carNames);
        RandomGenerator generator = new RandomGenerator();
        final int[][] moveHistory = race.getMoveHistoryFromEachCar(moveCount,
                generator.generateRandom2DArray(carNames.length, moveCount));
        final List<String> winners = race.getWinners(moveHistory);

        final OutputView outputView = new OutputView();
        outputView.printRaceResult(moveHistory, carNames, winners);
    }
}
