package cat.itacademy.s05.t01.n01.exception;

public class NoPlayersInTheDatabaseException extends RuntimeException{
    public NoPlayersInTheDatabaseException(String message) {
        super(message);
    }
}
