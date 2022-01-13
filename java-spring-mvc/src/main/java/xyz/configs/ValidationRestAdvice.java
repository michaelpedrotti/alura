package xyz.configs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import xyz.controllers.advices.FormBodyError;

@RestControllerAdvice
public class ValidationRestAdvice {

	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<FormBodyError> handle(MethodArgumentNotValidException e) {
		

		List<FormBodyError> errors = new ArrayList<FormBodyError>();

		e.getBindingResult().getFieldErrors().forEach(field -> {
			
			errors.add(new FormBodyError(
				field.getField(), 
				this.messageSource.getMessage(field, LocaleContextHolder.getLocale())
			));
		});
		
		return errors;
	}
}
