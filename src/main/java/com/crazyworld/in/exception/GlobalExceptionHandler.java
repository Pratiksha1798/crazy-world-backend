package com.crazyworld.in.exception;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({CountryNotFoundException.class})
	public ResponseEntity<Object> coutnryNotFoundException(CountryNotFoundException cf){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cf.getMessage());
	}
	
	
	@ExceptionHandler({GovernmentNotFoundException.class})
	public ResponseEntity<Object> governmentNotFoundException(GovernmentNotFoundException ge){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ge.getMessage());
	}
	
	
	
	@ExceptionHandler({LanguageNotFoundException.class})
	public ResponseEntity<Object> languageNotFoundException(LanguageNotFoundException le){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(le.getMessage());
	}
	
	@ExceptionHandler({DataNotFoundException.class})
	public ResponseEntity<Object> DataNotFoundException(DataNotFoundException le){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(le.getMessage());
	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<Map<String,List<String>>> handleValidationErrors(MethodArgumentNotValidException ex){
		List<String> errors=
				ex.getBindingResult().
				getFieldErrors().stream().
				map(FieldError::getDefaultMessage).collect(Collectors.toList());
		Map<String,List<String>> response=new HashMap<>();
		response.put("errors", errors);
		return new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}
	
	
	
}
