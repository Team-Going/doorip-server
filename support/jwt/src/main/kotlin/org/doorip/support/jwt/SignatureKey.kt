package org.doorip.support.jwt

import java.security.Key

interface SignatureKey {
    val key: Key?
}
