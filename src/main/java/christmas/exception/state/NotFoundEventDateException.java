package christmas.exception.state;

public class NotFoundEventDateException extends EventStateException {
    public NotFoundEventDateException() {
        super("방문 날짜를 찾지 못했습니다.");
    }
}
