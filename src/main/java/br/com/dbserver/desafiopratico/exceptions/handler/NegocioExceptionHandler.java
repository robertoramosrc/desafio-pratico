package exceptions.handler;

import exceptions.NegocioException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NegocioExceptionHandler {

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<ApiError> handle(NegocioException e){
        ApiError apiError = new ApiError("regra_de_negocio", e.getMessage());

        return ResponseEntity.badRequest().body(apiError);
    }

}
