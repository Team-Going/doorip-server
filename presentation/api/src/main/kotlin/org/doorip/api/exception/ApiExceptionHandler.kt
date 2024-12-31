package org.doorip.api.exception

import org.doorip.api.dto.ExceptionResponse
import org.doorip.domain.DooripException
import org.doorip.domain.InvalidRequestValueException
import org.doorip.domain.MethodNotAllowedException
import org.doorip.domain.UnknownException
import org.doorip.support.logging.logger
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
    private val logger = logger()

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    protected fun handleException(ex: MethodArgumentTypeMismatchException): ExceptionResponseEntity {
        logger.warn(ex.message, ex)
        return exceptionResponseFactory.create(InvalidRequestValueException)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleException(ex: MethodArgumentNotValidException): ExceptionResponseEntity {
        logger.warn(ex.message, ex)
        return exceptionResponseFactory.create(InvalidRequestValueException)
    }

    @ExceptionHandler(BindException::class)
    protected fun handleException(ex: BindException): ExceptionResponseEntity {
        logger.warn(ex.message, ex)
        return exceptionResponseFactory.create(InvalidRequestValueException)
    }

    @ExceptionHandler(NoResourceFoundException::class)
    protected fun handleException(ex: NoResourceFoundException): ExceptionResponseEntity {
        logger.warn(ex.message, ex)
        return exceptionResponseFactory.create(InvalidRequestValueException)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    protected fun handleException(ex: HttpRequestMethodNotSupportedException): ExceptionResponseEntity {
        logger.warn(ex.message, ex)
        return exceptionResponseFactory.create(MethodNotAllowedException)
    }

    @ExceptionHandler(DooripException::class)
    protected fun handleException(ex: DooripException): ExceptionResponseEntity {
        logger.error(ex.message, ex)
        return exceptionResponseFactory.create(ex)
    }

    @ExceptionHandler(Exception::class)
    protected fun handleException(ex: Exception): ExceptionResponseEntity {
        logger.error(ex.message, ex)
        return exceptionResponseFactory.create(UnknownException(ex))
    }
}
