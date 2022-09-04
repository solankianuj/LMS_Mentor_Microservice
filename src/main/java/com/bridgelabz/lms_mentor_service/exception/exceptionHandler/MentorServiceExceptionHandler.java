package com.bridgelabz.lms_mentor_service.exception.exceptionHandler;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.bridgelabz.lms_mentor_service.exception.MentorNotFound;
import com.bridgelabz.lms_mentor_service.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class MentorServiceExceptionHandler {

    /**
     * purpose:handling exception and providing custom message
     * @param mentor
     * @return
     */
    @ExceptionHandler(MentorNotFound.class)
    public ResponseEntity<Response> handleMentorException(MentorNotFound mentor){
        Response response=new Response();
        response.setStatusCode(400);
        response.setStatusMsg(mentor.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * purpose:handling exception and providing custom message
     * @param ad
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleException(MethodArgumentNotValidException ad) {
        List<ObjectError> objectErrors=ad.getBindingResult().getAllErrors();
        List<String> Message =objectErrors.stream().map(objErr-> objErr.getDefaultMessage()).collect(Collectors.toList());
        Response response = new Response();
        response.setStatusMsg(Message.toString());
        response.setStatusCode(400);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SignatureVerificationException.class)
    public ResponseEntity<Response> handleMentorException1(MentorNotFound mentor){
        Response response=new Response();
        response.setStatusCode(400);
        response.setStatusMsg(mentor.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
