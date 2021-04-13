package br.com.raynerweb.arquivos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionMapper {

    private static final String TIMESTAMP = "timestamp";
    private static final String STATUS = "status";
    private static final String ERROR = "error";
    private static final String MESSAGE = "message";
    private static final String PATH = "path";

    @ExceptionHandler(VirusDetectedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public Map<String, Object> handle(VirusDetectedException ex, HttpServletRequest request) {
        return getErroHttp(HttpStatus.UNSUPPORTED_MEDIA_TYPE, request, ex.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(IllegalStateException ex, HttpServletRequest request) {
        return getErroHttp(HttpStatus.BAD_REQUEST, request, ex.getMessage());
    }

    private Map<String, Object> getErroHttp(HttpStatus httpStatus, HttpServletRequest request, String mensagem) {
        Map<String, Object> erro = new HashMap<>();
        erro.put(TIMESTAMP, LocalDateTime.now());
        erro.put(STATUS, httpStatus.value());
        erro.put(ERROR, httpStatus.getReasonPhrase());
        erro.put(MESSAGE, mensagem);
        erro.put(PATH, request.getRequestURI());
        return erro;
    }

}
