package view;

import domain.NameRedundancyException;

import java.util.Scanner;

import static domain.Utils.hasRedundancy;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String[] getCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String[] names = scanner.nextLine().split(",");
        if (hasRedundancy(names)) {
            throw new NameRedundancyException();
        }
        return names;
    }

    public int getMoveCount() {
        System.out.println("시도할 횟수는 몇회인가요?");
        return scanner.nextInt();
    }
}
