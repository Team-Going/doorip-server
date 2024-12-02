package org.doorip.api.exception

import org.doorip.api.dto.ExceptionResponse
import org.doorip.domain.DooripException
import org.doorip.domain.InvalidRequestValueException
import org.doorip.domain.MethodNotAllowedException
import org.doorip.domain.UnknownException
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.resource.NoResourceFoundException

typealias ExceptionResponseEntity = ResponseEntity<ExceptionResponse>

@ControllerAdvice
internal class ApiExceptionHandler(
    private val exceptionResponseFactory: ExceptionResponseFactory,
) {

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    protected fun handleException(ex: MethodArgumentTypeMismatchException): ExceptionResponseEntity {
        return exceptionResponseFactory.create(InvalidRequestValueException)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleException(ex: MethodArgumentNotValidException): ExceptionResponseEntity {
        return exceptionResponseFactory.create(InvalidRequestValueException)
    }

    @ExceptionHandler(BindException::class)
    protected fun handleException(ex: BindException): ExceptionResponseEntity {
        return exceptionResponseFactory.create(InvalidRequestValueException)
    }

    @ExceptionHandler(NoResourceFoundException::class)
    protected fun handleException(ex: NoResourceFoundException): ExceptionResponseEntity {
        return exceptionResponseFactory.create(InvalidRequestValueException)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    protected fun handleException(ex: HttpRequestMethodNotSupportedException): ExceptionResponseEntity {
        return exceptionResponseFactory.create(MethodNotAllowedException)
    }

    @ExceptionHandler(DooripException::class)
    protected fun handleException(ex: DooripException): ExceptionResponseEntity {
        return exceptionResponseFactory.create(ex)
    }

    @ExceptionHandler(Exception::class)
    protected fun handleException(ex: Exception): ExceptionResponseEntity {
        return exceptionResponseFactory.create(UnknownException(ex))
    }
}
