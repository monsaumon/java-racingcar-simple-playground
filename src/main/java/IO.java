import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IO {
    private final Scanner scanner;

    public IO() {
        this.scanner = new Scanner(System.in);
    }

    public String[] getCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        return scanner.nextLine().split(",");
    }

    public int getMoveCount() {
        System.out.println("시도할 횟수는 몇회인가요?");
        return scanner.nextInt();
    }

    private void printOneMove(final int[] movedDistance, final String[] names) {
        if (names.length != movedDistance.length) {
            throw new WrongArrayLengthException();
        }
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i] + " : " + "-".repeat(movedDistance[i]));
        }
        System.out.println();
    }

    private int[] getMovedDistance(final int[][] moveHistory, final int step) {
        final int[] movedDistance = new int[moveHistory.length];
        for (int i = 0; i < moveHistory.length; i++) {
            movedDistance[i] = Arrays.stream(moveHistory[i], 0, step).sum();
        }
        return movedDistance;
    }

    public void printRaceResult(final int[][] moveHistory, final String[] names, final List<String> winners) {
        System.out.println("실행 결과");
        for (int i = 0; i < moveHistory[0].length; i++) {
            printOneMove(getMovedDistance(moveHistory, i + 1), names);
        }
        System.out.print(String.join(", ", winners) + "가 최종 우승했습니다.");
    }
}
