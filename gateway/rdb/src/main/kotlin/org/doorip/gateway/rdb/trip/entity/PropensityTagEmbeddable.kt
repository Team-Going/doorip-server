package org.doorip.gateway.rdb.trip.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.doorip.domain.trip.PropensityTag

@Embeddable
data class PropensityTagEmbeddable(
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

fun PropensityTagEmbeddable.toDomain(): PropensityTag {
    return PropensityTag(
        styles = listOf(styleA, styleB, styleC, styleD, styleE),
    )
}

fun PropensityTag.toEmbeddable(): PropensityTagEmbeddable {
    return PropensityTagEmbeddable(
        styleA = styles[0],
        styleB = styles[1],
        styleC = styles[2],
        styleD = styles[3],
        styleE = styles[4],
    )
}
