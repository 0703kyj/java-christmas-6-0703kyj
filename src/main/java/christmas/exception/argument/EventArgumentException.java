package christmas.exception.argument;

import christmas.exception.EventException;

public class EventArgumentException extends IllegalArgumentException implements EventException {
    private final String message;

    public EventArgumentException(String message) {
        this.message = PREFIX+message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
