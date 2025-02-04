package org.doorip.domain

sealed class DooripException(
    val code: String,
    message: String,
    cause: Throwable? = null,
) : RuntimeException(message, cause)

// Client Exception
sealed class ClientException(
    code: String,
    message: String,
) : DooripException(code, message)

data object UnauthorizedException : ClientException("e4030", "인가 과정에서 예외가 발생했습니다.") { private fun readResolve(): Any = UnauthorizedException }
data object UnauthenticatedException : ClientException("e4010", "인증 과정에서 예외가 발생했습니다. (ex. AT 만료 or RT 만료)") { private fun readResolve(): Any = UnauthenticatedException }

data object InvalidRequestValueException : ClientException("e4000", "잘못된 요청입니다.") { private fun readResolve(): Any = InvalidRequestValueException }
data object MethodNotAllowedException : ClientException("e4050", "잘못된 HTTP method 요청입니다.") { private fun readResolve(): Any = MethodNotAllowedException }
data object ConflictException : ClientException("e4090", "이미 존재하는 리소스입니다.") { private fun readResolve(): Any = ConflictException }
data object AlreadyExistingUserException : ClientException("e4091", "이미 존재하는 회원입니다.") { private fun readResolve(): Any = AlreadyExistingUserException }

// Server Exception
sealed class ServerException(
    code: String,
    message: String,
) : DooripException(code, message)

data object NotFoundException : ServerException("e4040", "대상을 찾을 수 없습니다.") { private fun readResolve(): Any = NotFoundException }
data object UserNotFoundException : ServerException("e4041", "존재하지 않는 회원입니다.") { private fun readResolve(): Any = UserNotFoundException }
data object InternalServerException : ServerException("e5000", "서버 내부 오류입니다.") { private fun readResolve(): Any = InternalServerException }

// Critical Exception
sealed class CriticalException(
    code: String,
    message: String,
    cause: Throwable? = null,
) : DooripException(code, message, cause)

class UnknownException(
    cause: Throwable? = null,
) : CriticalException("e6000", "정의되지 않은 예외입니다. (로그 확인이 필요합니다.)", cause)
