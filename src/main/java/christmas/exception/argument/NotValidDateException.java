package christmas.exception.argument;

import christmas.exception.argument.EventArgumentException;

public class NotValidDateException extends EventArgumentException {
    public NotValidDateException() {
        super("유효하지 않은 날짜입니다. 다시 입력해 주세요.\n");
    }
}
