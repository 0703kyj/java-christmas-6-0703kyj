package christmas.exception.argument;

public class OrderOnlyDrinkException extends EventArgumentException {
    public OrderOnlyDrinkException() {
        super("음료만 주문할 수 없습니다. 다시 입력해 주세요.\n");
    }
}
