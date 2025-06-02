package cat.itacademy.s05.t01.n01.exception;

import cat.itacademy.s05.t01.n01.dto.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Order(-2)
public class GlobalExceptionHandler implements WebExceptionHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        HttpStatus status = resolveHttpStatus(ex);

        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        ErrorResponse errorResponse = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                exchange.getRequest().getPath().value()
        );

        try {
            byte[] bytes = objectMapper.writeValueAsBytes(errorResponse);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            return exchange.getResponse().writeWith(Mono.just(buffer));
        } catch (Exception e) {

            System.err.println("Error serializing error response: " + e.getMessage());
            return exchange.getResponse().setComplete();
        }
    }


    private HttpStatus resolveHttpStatus(Throwable ex) {
        if (ex instanceof IllegalArgumentException) {
            return HttpStatus.BAD_REQUEST;
        } else if (ex instanceof NoPlayersInTheDatabaseException) {
            return HttpStatus.NOT_FOUND;
        } else if (ex instanceof PlayerNotFoundInDataBaseExeption) {
            return HttpStatus.NOT_FOUND;
        } else if (ex instanceof GameCreationParamsMissing) {
            return HttpStatus.BAD_REQUEST;
        } else if (ex instanceof GameAlreadyPlayedException) {
            return HttpStatus.CONFLICT;
        } else if (ex instanceof NoGamesInTheDatabaseException) {
            return HttpStatus.NOT_FOUND;
        } else if (ex instanceof GameNotFoundInDataBaseExeption) {
            return HttpStatus.NOT_FOUND;
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
