package LabAssistant;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class TAEntryNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(TAEntryNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String userNotFoundHandler(TAEntryNotFoundException ex) {
		return ex.getMessage();
	}
}