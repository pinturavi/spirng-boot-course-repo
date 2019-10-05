package com.example.cartservice.exception

import com.example.cartservice.model.ExceptionResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest

@RestController
@ControllerAdvice
class CustomResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun notFoundExceptionHandler(exceptin:NotFoundException, request:WebRequest): ResponseEntity<ExceptionResponse> {
        val exceptionResponse = ExceptionResponse(message="exception occured.", details = request.getDescription(false))
        return ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception::class)
    fun generalExceptionHandler(exceptin:Exception, request:WebRequest): ResponseEntity<ExceptionResponse> {
        val exceptionResponse = ExceptionResponse(message="exception occured.", details = request.getDescription(false))
        return ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}