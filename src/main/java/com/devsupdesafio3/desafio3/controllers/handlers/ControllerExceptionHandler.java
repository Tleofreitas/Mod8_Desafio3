package com.devsupdesafio3.desafio3.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsupdesafio3.desafio3.dto.CustomError;
import com.devsupdesafio3.desafio3.dto.ValidationError;
import com.devsupdesafio3.desafio3.services.exceptions.DataBaseException;
import com.devsupdesafio3.desafio3.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class) // Interceptar a Exceção
	public ResponseEntity<CustomError> resourceNotFoundException(ResourceNotFoundException e,
			HttpServletRequest request /* Obter a Url que deu exceção*/) {
		HttpStatus status = HttpStatus.NOT_FOUND; // Erro 404
		CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
		// Retornar o objeto
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<CustomError> database(DataBaseException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST; // Erro 400
		CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomError> methodArgumentNotValid(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY; // Erro 422
		ValidationError err = new ValidationError(Instant.now(), status.value(), "Dados Inválidos",
				request.getRequestURI());
		// Verificar se há erros e adicionar na lista de erros
		for (FieldError f : e.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(), f.getDefaultMessage());
		}
		// Retornar o objeto
		return ResponseEntity.status(status).body(err);
	}

}
