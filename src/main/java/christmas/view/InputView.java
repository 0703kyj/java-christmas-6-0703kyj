package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.EventDate;
import christmas.resource.DateValue;
import christmas.util.TypeChanger;
import java.util.function.Supplier;

public class InputView {
    private static final String INPUT_VISIT_DATE = DateValue.EVENT_MONTH+"월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    public EventDate readVisitDate(){
        EventDate visitDate = repeat(()-> new EventDate(inputVisitDate()));

        return visitDate;
    }

    private int inputVisitDate() {
        System.out.println(INPUT_VISIT_DATE);
        return TypeChanger.toIntDate(Console.readLine());
    }

    private <T> T repeat(Supplier<T> supplier) {
        while (true) {
            try {
                T result = supplier.get();
                return result;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception);
            }
        }
    }
}
