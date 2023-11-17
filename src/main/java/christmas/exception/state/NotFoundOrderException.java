package christmas.exception.state;

public class NotFoundOrderException extends EventStateException {

    public NotFoundOrderException() {
        super("주문을 찾지 못했습니다.");
    }
}
