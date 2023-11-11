package christmas.exception.state;

import christmas.exception.EventException;

public class EventStateException extends IllegalStateException implements EventException {
    private final String message;

    public EventStateException(String message) {
        this.message = PREFIX+message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
