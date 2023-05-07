package uz.market.uzum.handlers;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.market.uzum.dtos.AppErrorDTO;
import uz.market.uzum.exceptions.*;

import java.util.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppErrorDTO> handleUnknownExceptions(Exception e, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(
                new AppErrorDTO(
                        request.getRequestURI(),
                        e.getMessage(),
                        null,
                        400
                )
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<AppErrorDTO> handleRuntimeExceptions(RuntimeException e, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(
                new AppErrorDTO(
                        request.getRequestURI(),
                        e.getMessage(),
                        null,
                        400
                )
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<AppErrorDTO> handleItemNotFoundException(UserNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(404)
                .body(new AppErrorDTO(request.getRequestURI(), e.getMessage(), 404));
    }

    @ExceptionHandler(DuplicatePermissionCodeException.class)
    public ResponseEntity<AppErrorDTO> handleDuplicatePermissionCodeException(DuplicatePermissionCodeException e, HttpServletRequest request) {
        return ResponseEntity.status(400)
                .body(new AppErrorDTO(request.getRequestURI(), e.getMessage(), 400));
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<AppErrorDTO> handleItemNotFoundException(ItemNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(404)
                .body(new AppErrorDTO(request.getRequestURI(), e.getMessage(), 404));
    }

    @ExceptionHandler(OTPExpiredException.class)
    public ResponseEntity<AppErrorDTO> handleOTPExpiredException(OTPExpiredException e, HttpServletRequest request) {
        return ResponseEntity.status(400)
                .body(new AppErrorDTO(request.getRequestURI(), e.getMessage(), 400));
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<AppErrorDTO> handleDisabledException(DisabledException e, HttpServletRequest request) {
        return ResponseEntity.status(400)
                .body(new AppErrorDTO(request.getRequestURI(), e.getMessage(), 400));
    }

    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity<AppErrorDTO> handleCredentialsExpiredException(CredentialsExpiredException e, HttpServletRequest request) {
        return ResponseEntity.status(400)
                .body(new AppErrorDTO(request.getRequestURI(), e.getMessage(), 400));
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<AppErrorDTO> handleInsufficientAuthenticationException(InsufficientAuthenticationException e, HttpServletRequest request) {
        return ResponseEntity.status(403)
                .body(new AppErrorDTO(request.getRequestURI(), e.getMessage(), 403));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<AppErrorDTO> handleExpiredJwtException(ExpiredJwtException e, HttpServletRequest request) {
        return ResponseEntity.status(403)
                .body(new AppErrorDTO(request.getRequestURI(), e.getMessage(), 403));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<AppErrorDTO> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        return ResponseEntity.status(403)
                .body(new AppErrorDTO(request.getRequestURI(), ex.getMessage(), 403));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String errorMessage = "Input is not valid";
        Map<String, List<String>> errorBody = new HashMap<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            errorBody.compute(field, (s, values) -> {
                if (!Objects.isNull(values))
                    values.add(message);
                else
                    values = new ArrayList<>(Collections.singleton(message));
                return values;
            });
        }
        String errorPath = request.getRequestURI();
        AppErrorDTO errorDTO = new AppErrorDTO(errorPath, errorMessage, errorBody, 400);
        return ResponseEntity.status(400).body(errorDTO);
    }


    @ExceptionHandler(DuplicateUserRoleCodeException.class)
    public ResponseEntity<AppErrorDTO> handleDuplicateUserRoleCodeException(DuplicateUserRoleCodeException e, HttpServletRequest request) {
        return ResponseEntity.status(400)
                .body(new AppErrorDTO(request.getRequestURI(), e.getMessage(), null, 400));
    }

    @ExceptionHandler(DuplicatePermissionForSingleRoleException.class)
    public ResponseEntity<AppErrorDTO> handleDuplicatePermissionForSingleRoleException(DuplicatePermissionForSingleRoleException e, HttpServletRequest request) {
        return ResponseEntity.status(400)
                .body(new AppErrorDTO(request.getRequestURI(), e.getMessage(), null, 400));
    }


}
