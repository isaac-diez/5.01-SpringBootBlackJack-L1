package cat.itacademy.s05.t01.n01.exception;

public class PlayerNotFoundInDataBaseExeption extends RuntimeException {
    public PlayerNotFoundInDataBaseExeption(String message) {
        super(message);
    }
}
