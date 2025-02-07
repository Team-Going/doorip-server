package org.doorip.api.exception

import org.doorip.api.dto.ExceptionResponse
import org.doorip.domain.AlreadyExistingParticipantException
import org.doorip.domain.AlreadyExistingUserException
import org.doorip.domain.ClientException
import org.doorip.domain.ConflictException
import org.doorip.domain.CriticalException
import org.doorip.domain.DooripException
import org.doorip.domain.MethodNotAllowedException
import org.doorip.domain.NotFoundException
import org.doorip.domain.ServerException
import org.doorip.domain.TripNotFoundException
import org.doorip.domain.UnauthenticatedException
import org.doorip.domain.UnauthorizedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
internal class ExceptionResponseFactory {

    fun create(exception: DooripException): ResponseEntity<ExceptionResponse> {
        val httpStatus = exception.getHttpStatus()

        val exceptionResponse = ExceptionResponse(
            status = httpStatus.value(),
            code = exception.code,
            message = exception.message,
        )

        return ResponseEntity.status(httpStatus)
            .body(exceptionResponse)
    }
}

internal fun DooripException.getHttpStatus(): HttpStatus =
    when (this) {
        is UnauthorizedException -> HttpStatus.FORBIDDEN
        is UnauthenticatedException -> HttpStatus.UNAUTHORIZED

        MethodNotAllowedException -> HttpStatus.METHOD_NOT_ALLOWED

        ConflictException -> HttpStatus.CONFLICT
        AlreadyExistingUserException -> HttpStatus.CONFLICT
        AlreadyExistingParticipantException -> HttpStatus.CONFLICT

        NotFoundException -> HttpStatus.NOT_FOUND
        TripNotFoundException -> HttpStatus.NOT_FOUND

        is ClientException -> HttpStatus.BAD_REQUEST
        is ServerException, is CriticalException -> HttpStatus.INTERNAL_SERVER_ERROR
    }
