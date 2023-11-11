package christmas.domain;

import christmas.exception.argument.NotValidDateException;
import christmas.resource.DateValue;
import java.time.DateTimeException;
import java.time.LocalDate;

public class EventDate {
    private final LocalDate date;

    private EventDate(int year, int month, int day) {
        try {
            date = LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw new NotValidDateException();
        }
    }

    public EventDate(int day){
        this(DateValue.EVENT_YEAR.getValue(),
                DateValue.EVENT_MONTH.getValue(),
                day);
    }

    public int getDay(){
        return date.getDayOfMonth();
    }
}
