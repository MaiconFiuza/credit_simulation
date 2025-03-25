package com.fiuza.creditas.application.handler

import com.fiuza.creditas.core.dto.errors.BadRequestExceptionDto
import com.fiuza.creditas.core.dto.errors.InternalServerErrorExceptionDto
import com.fiuza.creditas.core.exceptions.BadRequestException
import com.fiuza.creditas.core.exceptions.InternalServerErrorException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@ControllerAdvice
class ControllerExceptionHandler {

    @ExceptionHandler(BadRequestException::class)
    fun handlerBadRequestException(
        exception: BadRequestException
    ): ResponseEntity<BadRequestExceptionDto> {
        val status = HttpStatus.BAD_REQUEST
        return ResponseEntity.status(status.value())
            .body(BadRequestExceptionDto(exception.message, status.value()))
    }

    @ExceptionHandler(InternalServerErrorException::class)
    fun handlerInternalServerErrorException(
        exception: InternalServerErrorException
    ): ResponseEntity<InternalServerErrorExceptionDto> {
        val status = HttpStatus.BAD_REQUEST
        return ResponseEntity.status(status.value())
            .body(InternalServerErrorExceptionDto(exception.message, status.value()))
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatch(ex: MethodArgumentTypeMismatchException?): ResponseEntity<BadRequestExceptionDto> {
        val status = HttpStatus.BAD_REQUEST
        return ResponseEntity.status(status.value())
            .body(BadRequestExceptionDto("Formato incorreto, por favor insira novamente", status.value()))
    }
}
