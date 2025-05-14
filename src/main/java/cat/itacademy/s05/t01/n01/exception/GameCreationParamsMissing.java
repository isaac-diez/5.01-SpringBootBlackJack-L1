package cat.itacademy.s05.t01.n01.exception;

public class GameCreationParamsMissing extends RuntimeException {
    public GameCreationParamsMissing(String message) {
        super(message);
    }
}
