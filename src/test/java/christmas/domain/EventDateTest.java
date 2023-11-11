package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.argument.NotValidDateException;
import christmas.util.TypeChanger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EventDateTest {

    @ParameterizedTest
    @ValueSource(ints = {0,32})
    @DisplayName("입력 받은 날짜는 12월에 속해야 한다.")
    void notValidDateTest(int day) {
        assertThatThrownBy(() -> new EventDate(day))
                .isInstanceOf(NotValidDateException.class);
    }
    @ParameterizedTest
    @ValueSource(strings = {"일","이십삼"})
    @DisplayName("입력 받은 날짜는 숫자여야 한다.")
    void inputStringToDateTest(String day) {
        assertThatThrownBy(() -> TypeChanger.toIntDate(day))
                .isInstanceOf(NotValidDateException.class);
    }
}
