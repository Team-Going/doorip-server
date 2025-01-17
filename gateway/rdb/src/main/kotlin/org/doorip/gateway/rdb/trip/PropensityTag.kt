package org.doorip.gateway.rdb.trip

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class PropensityTag(
    @Column(name = "style_a", columnDefinition = "integer")
    val styleA: Int,
    @Column(name = "style_b", columnDefinition = "integer")
    val styleB: Int,
    @Column(name = "style_c", columnDefinition = "integer")
    val styleC: Int,
    @Column(name = "style_d", columnDefinition = "integer")
    val styleD: Int,
    @Column(name = "style_e", columnDefinition = "integer")
    val styleE: Int,
)
