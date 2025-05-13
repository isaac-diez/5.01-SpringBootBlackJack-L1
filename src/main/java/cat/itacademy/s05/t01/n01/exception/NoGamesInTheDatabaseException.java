package cat.itacademy.s05.t01.n01.exception;

public class NoGamesInTheDatabaseException extends RuntimeException {
    public NoGamesInTheDatabaseException(String message) {
        super(message);
    }
}
