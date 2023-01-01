package com.ricartedev.delivery.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ricartedev.delivery.domain.exception.EntidadeNaoEncontradaException;
import com.ricartedev.delivery.domain.exception.NegocioException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
					HttpHeaders headers, HttpStatus status, WebRequest request) {
			
			List<Problema.Campo> campos = ex
							.getBindingResult()
							.getAllErrors().stream()
							.map(error -> {
									String nome = ((FieldError) error).getField();
									String mensagem = messageSource
									.getMessage(error, LocaleContextHolder
									.getLocale());
									return new Problema
									.Campo(nome, mensagem);
							})
							.collect(Collectors.toList());
			
			Problema problema = new Problema();
			problema.setStatus(status.value());
			problema.setDataHora(OffsetDateTime.now());
			problema.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
			problema.setCampos(campos);
			
			return handleExceptionInternal(ex, problema, headers, status, request);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(NegocioException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;

		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo(ex.getMessage());
	
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);

	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo(ex.getMessage());
	
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);

	}

}
