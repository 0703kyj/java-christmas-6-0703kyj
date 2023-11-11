package christmas.exception.argument;

public class NotValidOrderException extends EventArgumentException{
    public NotValidOrderException() {
        super("유효하지 않은 주문입니다. 다시 입력해 주세요.\n");
    }
}
