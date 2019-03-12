package LabAssistant;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class StudentEntryNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(StudentEntryNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String userNotFoundHandler(StudentEntryNotFoundException ex) {
		return ex.getMessage();
	}
}