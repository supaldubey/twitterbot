/**
 * 
 */
package com.cubestack.social.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author supal
 *
 */
@RestController
public class AppRestController {

    private static final String MSG_SEPARATOR = ", ";

    @ExceptionHandler
    public ResponseEntity<Response> handle(Exception exception) {
	exception.printStackTrace();

	Response response = new Response();
	response.setSuccess(false);

	if (exception instanceof MethodArgumentNotValidException) {
	    MethodArgumentNotValidException validationException = (MethodArgumentNotValidException) exception;
	    StringBuilder errorMsg = new StringBuilder();
	    for (FieldError error : validationException.getBindingResult().getFieldErrors()) {
		errorMsg.append(error.getDefaultMessage()).append(MSG_SEPARATOR);
	    }
	    // Trim the last Comma
	    errorMsg = new StringBuilder(errorMsg.substring(0, errorMsg.length() - 2));
	    response.setMessage(errorMsg.toString());
	} else {
	    response.setMessage(exception.getMessage());
	}
	return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
