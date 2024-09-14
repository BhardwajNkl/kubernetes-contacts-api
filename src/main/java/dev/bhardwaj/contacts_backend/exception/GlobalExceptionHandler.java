package dev.bhardwaj.contacts_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> notFoundHandler(NotFoundException exception) {
		log.error("NOT FOUND: "+exception);
		ErrorResponse response = new ErrorResponse(404, exception.getMessage());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> generalHandler(Exception exception) {
		log.error("SERVER ERROR: "+exception);
		ErrorResponse response = new ErrorResponse(500, exception.getMessage());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@AllArgsConstructor
@Getter
@Setter
class ErrorResponse{
	private int code;
	private String message;
}