package cat.itacademy.s05.t01.n01.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(NoPlayersInTheDatabaseException.class)
    public ResponseEntity<Object> handleNoPlayersInDB(NoPlayersInTheDatabaseException ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.NO_CONTENT, request);
    }

    @ExceptionHandler(PlayerNotFoundInDataBaseExeption.class)
    public ResponseEntity<Object> handleNoPlayerFoundInDB(PlayerNotFoundInDataBaseExeption ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.NO_CONTENT, request);
    }

    @ExceptionHandler(GameCreationParamsMissing.class)
    public ResponseEntity<Object> handleGameCreationParamsMissing(GameCreationParamsMissing ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(GameAlreadyPlayedException.class)
    public ResponseEntity<Object> handleAlreadyPlayed(GameAlreadyPlayedException ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(NoGamesInTheDatabaseException.class)
    public ResponseEntity<Object> handleNoGamesInDB(NoGamesInTheDatabaseException ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.NO_CONTENT, request);
    }

    @ExceptionHandler(GameNotFoundInDataBaseExeption.class)
    public ResponseEntity<Object> handleGameNotFoundInDataBase(GameNotFoundInDataBaseExeption ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.NO_CONTENT, request);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception ex, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, status);
    }
}

