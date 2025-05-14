package cat.itacademy.s05.t01.n01.exception;

public class GameAlreadyPlayedException extends RuntimeException {
    public GameAlreadyPlayedException(String message) {
        super(message);
    }
}
