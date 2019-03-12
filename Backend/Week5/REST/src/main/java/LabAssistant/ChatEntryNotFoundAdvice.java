package LabAssistant;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ChatEntryNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(ChatEntryNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String chatNotFoundHandler(ChatEntryNotFoundException ex) {
		return ex.getMessage();
	}
}