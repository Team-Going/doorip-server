package org.doorip.core

import org.doorip.domain.InvalidRequestValueException
import org.springframework.stereotype.Service

@Service
class TestService {

    fun throwDooripException() {
        throw InvalidRequestValueException
    }
}
