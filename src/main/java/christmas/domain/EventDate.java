package christmas.domain;

import christmas.exception.argument.NotValidDateException;
import christmas.resource.DateValue;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

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

    public String getDayOfWeek(){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN);
    }
}
