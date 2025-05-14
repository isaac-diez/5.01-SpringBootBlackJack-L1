package cat.itacademy.s05.t01.n01.exception;

public class GameNotFoundInDataBaseExeption extends RuntimeException {
    public GameNotFoundInDataBaseExeption(String message) {
        super(message);
    }
}
