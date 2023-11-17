package christmas.exception.argument;

public class OverOrderCountException extends EventArgumentException {
    public OverOrderCountException() {
        super("매뉴는 20가지 이하로 주문해야 합니다. 다시 입력해 주세요.\n");
    }
}
